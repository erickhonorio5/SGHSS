package gestao.sghss.controllers;

import gestao.sghss.controllers.dtos.requests.CreateWorkScheduleRequestDTO;
import gestao.sghss.controllers.dtos.requests.PatchWorkScheduleRequestDTO;
import gestao.sghss.controllers.dtos.requests.UpdateWorkScheduleRequestDTO;
import gestao.sghss.controllers.dtos.requests.WeeklyWorkScheduleRequestDTO;
import gestao.sghss.controllers.dtos.responses.WorkScheduleResponseDTO;
import gestao.sghss.controllers.mappers.WorkScheduleControllerMapper;
import gestao.sghss.usecases.WorkScheduleUseCase.CreateWorkScheduleUseCase;
import gestao.sghss.usecases.WorkScheduleUseCase.DeleteWorkScheduleUseCase;
import gestao.sghss.usecases.WorkScheduleUseCase.FindWorkScheduleByIdUseCase;
import gestao.sghss.usecases.WorkScheduleUseCase.ListWorkSchedulesByProfessionalUseCase;
import gestao.sghss.usecases.WorkScheduleUseCase.PatchWorkScheduleUseCase;
import gestao.sghss.usecases.WorkScheduleUseCase.UpdateWorkScheduleUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/professional/{professionalId}/work-schedules")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "WorkSchedule", description = "Gestão de horários dos profissionais")
public class WorkScheduleController {

    private final CreateWorkScheduleUseCase createWorkScheduleUseCase;
    private final ListWorkSchedulesByProfessionalUseCase listWorkSchedulesByProfessionalUseCase;
    private final DeleteWorkScheduleUseCase deleteWorkScheduleUseCase;
    private final UpdateWorkScheduleUseCase updateWorkScheduleUseCase;
    private final FindWorkScheduleByIdUseCase findWorkScheduleByIdUseCase;
    private final PatchWorkScheduleUseCase patchWorkScheduleUseCase;

    @Qualifier("workScheduleControllerMapper")
    private final WorkScheduleControllerMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar horário de trabalho", description = "Define um novo horário para o profissional")
    public WorkScheduleResponseDTO create(@PathVariable Long professionalId, @RequestBody @Valid CreateWorkScheduleRequestDTO request) {
        var schedule = mapper.toDomain(request);
        var created = createWorkScheduleUseCase.execute(professionalId, schedule);
        return mapper.toResponse(created);
    }

    @GetMapping
    @Operation(summary = "Listar horários de trabalho", description = "Retorna todos os horários de trabalho do profissional")
    public List<WorkScheduleResponseDTO> listAll(@PathVariable Long professionalId) {
        return listWorkSchedulesByProfessionalUseCase.execute(professionalId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Excluir horário de trabalho", description = "Remove um horário de trabalho específico do profissional")
    public void delete(@PathVariable Long professionalId, @PathVariable Long scheduleId) {
        deleteWorkScheduleUseCase.execute(professionalId, scheduleId);
    }

    @PostMapping("/week")
    @Operation(summary = "Definir agenda da semana inteira", description = "Define os horários de trabalho para todos os dias da semana de uma vez")
    @ResponseStatus(CREATED)
    public List<WorkScheduleResponseDTO> createWeek(@PathVariable Long professionalId, @RequestBody @Valid WeeklyWorkScheduleRequestDTO request) {
        var schedules = request.schedules().stream().map(mapper::toDomain).toList();

        return createWorkScheduleUseCase.createWeek(professionalId, schedules).stream().map(mapper::toResponse).toList();
    }

    @PutMapping("/{scheduleId}")
    public WorkScheduleResponseDTO update(@PathVariable Long professionalId, @PathVariable Long scheduleId, @RequestBody @Valid UpdateWorkScheduleRequestDTO request) {
        var updated = mapper.toDomainUpdate(request);
        return mapper.toResponse(updateWorkScheduleUseCase.execute(professionalId, scheduleId, updated));
    }

    @GetMapping("/{scheduleId}")
    @Operation(summary = "Buscar horário de trabalho específico", description = "Retorna os dados de um horário de trabalho pelo ID")
    public WorkScheduleResponseDTO findById(@PathVariable Long professionalId, @PathVariable Long scheduleId) {
        return mapper.toResponse(findWorkScheduleByIdUseCase.execute(professionalId, scheduleId));
    }

    @PatchMapping("/{scheduleId}")
    @Operation(summary = "Atualizar parcialmente horário de trabalho")
    public WorkScheduleResponseDTO patch(@PathVariable Long professionalId, @PathVariable Long scheduleId, @RequestBody PatchWorkScheduleRequestDTO request) {
        var patch = mapper.toDomainPatch(request);
        return mapper.toResponse(patchWorkScheduleUseCase.execute(professionalId, scheduleId, patch));
    }
}

