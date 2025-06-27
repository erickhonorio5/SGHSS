package gestao.sghss.controllers;

import gestao.sghss.controllers.dtos.requests.PatientPatchRequestDTO;
import gestao.sghss.controllers.dtos.requests.PatientRequestDTO;
import gestao.sghss.controllers.dtos.responses.PatientResponseDTO;
import gestao.sghss.controllers.mappers.PatientControllerMapper;
import gestao.sghss.usecases.PatientUseCase.CreatePatient;
import gestao.sghss.usecases.PatientUseCase.DeactivatePatient;
import gestao.sghss.usecases.PatientUseCase.DeletePatient;
import gestao.sghss.usecases.PatientUseCase.FindAllPatients;
import gestao.sghss.usecases.PatientUseCase.FindPatientByCpf;
import gestao.sghss.usecases.PatientUseCase.FindPatientById;
import gestao.sghss.usecases.PatientUseCase.PatchPatient;
import gestao.sghss.usecases.PatientUseCase.ReactivatePatient;
import gestao.sghss.usecases.PatientUseCase.UpdatePatient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "Patient Controller", description = "Operações relacionadas aos pacientes")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final CreatePatient createPatient;
    private final PatientControllerMapper mapper;
    private final FindAllPatients findAllPatients;
    private final FindPatientByCpf findPatientByCpf;
    private final FindPatientById findPatientById;
    private final DeletePatient deletePatient;
    private final DeactivatePatient deactivatePatient;
    private final ReactivatePatient reactivatePatient;
    private final UpdatePatient updatePatient;
    private final PatchPatient patchPatient;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar paciente", description = "Cadastra um novo paciente no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public PatientResponseDTO create(@RequestBody @Valid PatientRequestDTO request) {
        var domain = mapper.toDomain(request);
        var createdPatient = createPatient.execute(domain);
        return mapper.toResponse(createdPatient);
    }

    @GetMapping
    @ResponseStatus(OK)
    @Operation(summary = "Listar pacientes", description = "Retorna uma lista paginada de pacientes, podendo filtrar por nome e appointmentStatus (ativo/inativo)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacientes listados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public Page<PatientResponseDTO> list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "true") Boolean active,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        var page = findAllPatients.execute(name, active, pageable);
        return page.map(mapper::toResponse);
    }

    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Buscar paciente por CPF", description = "Retorna os dados de um paciente com base no CPF informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public PatientResponseDTO findByCpf(@PathVariable @CPF String cpf) {
        var patient = findPatientByCpf.execute(cpf);
        return mapper.toResponse(patient);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por ID", description = "Retorna os dados de um paciente com base no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public PatientResponseDTO findById(@PathVariable @Valid Long id){
        var patient = findPatientById.execute(id);
        return mapper.toResponse(patient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Desativar paciente", description = "Desativa logicamente um paciente, mantendo seus dados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente desativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public void deactivate(@PathVariable Long id) {
        deactivatePatient.execute(id);
    }

    @PutMapping("/{id}/activate")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Reativar paciente", description = "Reativa um paciente previamente desativado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente reativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public void activate(@PathVariable Long id) {
        reactivatePatient.execute(id);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir paciente permanentemente", description = "Remove definitivamente um paciente do sistema (delete real)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public void delete(@PathVariable Long id) {
        deletePatient.execute(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar parcialmente paciente", description = "Atualiza parcialmente os dados de um paciente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    public PatientResponseDTO patch(
            @PathVariable Long id,
            @Valid @RequestBody PatientPatchRequestDTO patchDto
    ) {
        var patientDomain = mapper.toDomain(patchDto);
        var updatedPatient = patchPatient.execute(id, patientDomain);

        return mapper.toResponse(updatedPatient);
    }
}
