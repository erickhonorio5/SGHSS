package gestao.sghss.controllers;

import gestao.sghss.controllers.dtos.requests.CreateWorkScheduleRequestDTO;
import gestao.sghss.controllers.dtos.responses.WorkScheduleResponseDTO;
import gestao.sghss.controllers.mappers.WorkScheduleControllerMapper;
import gestao.sghss.usecases.WorkScheduleUseCase.CreateWorkScheduleUseCase;
import gestao.sghss.usecases.WorkScheduleUseCase.DeleteWorkScheduleUseCase;
import gestao.sghss.usecases.WorkScheduleUseCase.ListWorkSchedulesByProfessionalUseCase;
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

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/professional/{professionalId}/work-schedules")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "WorkSchedule", description = "Gestão de horários dos profissionais")
public class WorkScheduleController {

    private final CreateWorkScheduleUseCase createWorkScheduleUseCase;
    private final ListWorkSchedulesByProfessionalUseCase listWorkSchedulesByProfessionalUseCase;
    private final DeleteWorkScheduleUseCase deleteWorkScheduleUseCase;
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
}

