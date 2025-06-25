package gestao.sghss.controllers;

import gestao.sghss.controllers.dtos.requests.CreateSpecialtyRequestDTO;
import gestao.sghss.controllers.dtos.responses.SpecialtyResponseDTO;
import gestao.sghss.controllers.mappers.SpecialtyControllerMapper;
import gestao.sghss.usecases.SpecialityUseCase.CreateSpecialtyUseCase;
import gestao.sghss.usecases.SpecialityUseCase.SpecialityUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/speciality")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Speciality", description = "Operações de gestão de especialidade de saúde")
public class SpecialityController {

    private final CreateSpecialtyUseCase createSpecialtyUseCase;
    private final SpecialtyControllerMapper specialtyControllerMapper;
    private final SpecialityUseCase specialityUseCase;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar especialidade", description = "Cadastra uma nova especialidade no sistema")
    public SpecialtyResponseDTO create (@RequestBody @Valid CreateSpecialtyRequestDTO request) {
        var specialty = specialtyControllerMapper.toDomain(request);
        var createdSpecialty = createSpecialtyUseCase.execute(specialty);
        return specialtyControllerMapper.toResponse(createdSpecialty);
    }

    @GetMapping
    @ResponseStatus(OK)
    @Operation(summary = "Listar especialidades", description = "Retorna uma lista com todas as especialidades cadastradas no sistema")
    public List<SpecialtyResponseDTO> listAll(){
        return specialityUseCase.listAll().stream().map(specialtyControllerMapper::toResponse).toList();
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Buscar especialidade por ID", description = "Retorna os dados de uma especialidade com base no ID informado")
    public SpecialtyResponseDTO findById(@Valid Long id){
        var speciality = specialityUseCase.findWithId(id);
        return specialtyControllerMapper.toResponse(speciality);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir especialidade", description = "Remove definitivamente uma especialidade do sistema (delete real)")
    public void delete(@PathVariable Long id) {
        specialityUseCase.delete(id);
    }
}
