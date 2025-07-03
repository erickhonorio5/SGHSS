package gestao.sghss.controllers;

import gestao.sghss.controllers.dtos.DocumentTypeDTO;
import gestao.sghss.controllers.dtos.requests.CreateProfessionalRequestDTO;
import gestao.sghss.controllers.dtos.requests.UpdateProfessionalRequestDTO;
import gestao.sghss.controllers.dtos.responses.ProfessionalResponseDTO;
import gestao.sghss.controllers.mappers.ProfessionalControllerMapper;
import gestao.sghss.domain.enums.DocumentType;
import gestao.sghss.usecases.ProfessionalUseCase.CreateProfessionalUseCase;
import gestao.sghss.usecases.ProfessionalUseCase.DeleteProfessionalUseCase;
import gestao.sghss.usecases.ProfessionalUseCase.GetProfessionalByIdUseCase;
import gestao.sghss.usecases.ProfessionalUseCase.ListProfessionalsBySpecialtyUseCase;
import gestao.sghss.usecases.ProfessionalUseCase.ListProfessionalsUseCase;
import gestao.sghss.usecases.ProfessionalUseCase.UpdateProfessionalUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/professional")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Professional Controller", description = "Operações de gestão de profissionais de saúde")
public class ProfessionalController {

    private final CreateProfessionalUseCase createProfessionalUseCase;
    private final ProfessionalControllerMapper professionalControllerMapper;
    private final GetProfessionalByIdUseCase getProfessionalByIdUseCase;
    private final ListProfessionalsUseCase listProfessionalsUseCase;
    private final UpdateProfessionalUseCase updateProfessionalUseCase;
    private final DeleteProfessionalUseCase deleteProfessionalUseCase;
    private final ListProfessionalsBySpecialtyUseCase listProfessionalsBySpecialtyUseCase;
    private final ProfessionalControllerMapper mapper;

    @PostMapping
    @ResponseStatus(OK)
    @Operation(summary = "Criar profissional", description = "Cadastra um novo profissional no sistema")
    public ProfessionalResponseDTO create (@RequestBody @Valid CreateProfessionalRequestDTO request) {
        var partialDomain = professionalControllerMapper.toDomain(request);
        var created = createProfessionalUseCase.execute(partialDomain, request.specialtyIds());
        return professionalControllerMapper.toResponse(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar profissional por ID", description = "Retorna os dados do profissional pelo ID")
    public ProfessionalResponseDTO getById(@PathVariable Long id) {
        var professional = getProfessionalByIdUseCase.execute(id);
        return mapper.toResponse(professional);
    }

    @GetMapping
    @Operation(summary = "Listar profissionais", description = "Lista todos os profissionais cadastrados")
    public List<ProfessionalResponseDTO> listAll() {
        log.info("Listando todos os profissionais");
        var professionals = listProfessionalsUseCase.execute();
        return professionals.stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/especiality/{specialtyId}")
    @Operation(summary = "Listar profissionais por especialidade", description = "Retorna todos os profissionais vinculados à especialidade informada")
    public List<ProfessionalResponseDTO> listBySpecialty(@PathVariable Long specialtyId) {
        log.info("Listando profissionais por especialidade ID: {}", specialtyId);
        return listProfessionalsBySpecialtyUseCase.execute(specialtyId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar profissional", description = "Atualiza os dados do profissional pelo ID")
    public ProfessionalResponseDTO update(@PathVariable Long id, @RequestBody @Valid UpdateProfessionalRequestDTO request) {
        log.info("Atualizando profissional ID: {}", id);
        var professional = mapper.toDomain(request);
        professional.setId(id);
        var updated = updateProfessionalUseCase.execute(professional, request.specialtyIds());
        return mapper.toResponse(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir profissional", description = "Remove um profissional do sistema pelo ID")
    public void delete(@PathVariable Long id) {
        log.info("Requisição de exclusão para profissional ID: {}", id);
        deleteProfessionalUseCase.execute(id);
    }

    @GetMapping("/document-types")
    public List<DocumentTypeDTO> getDocumentTypes() {
        return Arrays.stream(DocumentType.values())
                .map(dt -> new DocumentTypeDTO(dt.name(), dt.getDescription()))
                .toList();
    }
}