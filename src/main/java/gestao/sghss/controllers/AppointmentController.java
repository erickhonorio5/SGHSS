package gestao.sghss.controllers;

import gestao.sghss.controllers.dtos.requests.AppointmentCreateRequestDTO;
import gestao.sghss.controllers.dtos.requests.AppointmentRescheduleRequestDTO;
import gestao.sghss.controllers.dtos.responses.AppointmentResponseDTO;
import gestao.sghss.controllers.mappers.AppointmentControllerMapper;
import gestao.sghss.usecases.AppointmentUseCase.CancelAppointmentUseCase;
import gestao.sghss.usecases.AppointmentUseCase.CompleteAppointmentUseCase;
import gestao.sghss.usecases.AppointmentUseCase.ConfirmAppointmentUseCase;
import gestao.sghss.usecases.AppointmentUseCase.CreateAppointmentUseCase;
import gestao.sghss.usecases.AppointmentUseCase.GetAppointmentByIdUseCase;
import gestao.sghss.usecases.AppointmentUseCase.ListAppointmentsByPatientIdUseCase;
import gestao.sghss.usecases.AppointmentUseCase.ListAppointmentsByProfessionalIdUseCase;
import gestao.sghss.usecases.AppointmentUseCase.ListAvailableDaysUseCase;
import gestao.sghss.usecases.AppointmentUseCase.ListAvailableSlotsByDayUseCase;
import gestao.sghss.usecases.AppointmentUseCase.ListAvailableTimeSlotsUseCase;
import gestao.sghss.usecases.AppointmentUseCase.RescheduleAppointmentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointments")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Appointment Controller", description = "Operações de agendamento de consultas")
public class AppointmentController {

    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final GetAppointmentByIdUseCase getAppointmentByIdUseCase;
    private final ListAppointmentsByProfessionalIdUseCase listAppointmentsByProfessionalIdUseCase;
    private final ListAppointmentsByPatientIdUseCase listAppointmentsByPatientIdUseCase;
    private final CancelAppointmentUseCase cancelAppointmentUseCase;
    private final ConfirmAppointmentUseCase confirmAppointmentUseCase;
    private final CompleteAppointmentUseCase completeAppointmentUseCase;
    private final RescheduleAppointmentUseCase rescheduleAppointmentUseCase;
    private final ListAvailableTimeSlotsUseCase listAvailableTimeSlotsUseCase;
    private final ListAvailableDaysUseCase listAvailableDaysUseCase;
    private final ListAvailableSlotsByDayUseCase listAvailableSlotsByDayUseCase;
    private final AppointmentControllerMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Agendar consulta", description = "Agendar uma nova consulta")
    public AppointmentResponseDTO create(@RequestBody @Valid AppointmentCreateRequestDTO request) {
        return mapper.toResponse(createAppointmentUseCase.execute(mapper.toDomain(request)));
    }

    @GetMapping("/{appointmentId}")
    @ResponseStatus(OK)
    @Operation(summary = "Buscar consulta por ID", description = "Retorna os dados de uma consulta com base no ID informado")
    public AppointmentResponseDTO getById(@PathVariable @Valid Long appointmentId) {
        return mapper.toResponse(getAppointmentByIdUseCase.execute(appointmentId));
    }

    @GetMapping("/professional/{professionalId}/appointments")
    @ResponseStatus(OK)
    @Operation(summary = "Listar consultas por profissional", description = "Retorna todas as consultas de um profissional")
    public List<AppointmentResponseDTO> getByProfessionalId(@PathVariable Long professionalId) {
        var appointments = listAppointmentsByProfessionalIdUseCase.execute(professionalId);
        return appointments.stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/patient/{patientId}")
    @ResponseStatus(OK)
    @Operation(summary = "Listar consultas por paciente", description = "Retorna todas as consultas de um paciente")
    public List<AppointmentResponseDTO> getByPatientId(@PathVariable Long patientId) {
        var appointments = listAppointmentsByPatientIdUseCase.execute(patientId);
        return appointments.stream().map(mapper::toResponse).toList();
    }

    @PatchMapping("/{appointmentId}/cancel")
    @Operation(summary = "Cancelar consulta", description = "Cancela uma consulta existente")
    public AppointmentResponseDTO cancel(@PathVariable Long appointmentId) {
        var canceled = cancelAppointmentUseCase.execute(appointmentId);
        return mapper.toResponse(canceled);
    }

    @PatchMapping("/{appointmentId}/confirm")
    @Operation(summary = "Confirmar consulta", description = "Confirma uma consulta agendada")
    @ResponseStatus(OK)
    public AppointmentResponseDTO confirm(@PathVariable Long appointmentId) {
        var confirmed = confirmAppointmentUseCase.execute(appointmentId);
        return mapper.toResponse(confirmed);
    }

    @PatchMapping("/{appointmentId}/complete")
    @Operation(summary = "Concluir consulta", description = "Conclui uma consulta confirmada ou agendada")
    @ResponseStatus(OK)
    public AppointmentResponseDTO complete(@PathVariable Long appointmentId) {
        var completed = completeAppointmentUseCase.execute(appointmentId);
        return mapper.toResponse(completed);
    }

    @PatchMapping("/{appointmentId}/reschedule")
    @Operation(summary = "Reagendar consulta", description = "Permite reagendar uma consulta")
    @ResponseStatus(OK)
    public AppointmentResponseDTO reschedule(
            @PathVariable Long appointmentId,
            @RequestBody @Valid AppointmentRescheduleRequestDTO request) {
        var rescheduled = rescheduleAppointmentUseCase.execute(appointmentId, request.newAppointmentDate(), request.newAppointmentTime());
        return mapper.toResponse(rescheduled);
    }

    @GetMapping("/professional/{professionalId}/available-slots")
    @Operation(summary = "Listar horários disponíveis", description = "Lista os horários disponíveis para um profissional em uma data específica")
    public List<LocalTime> getAvailableSlots(@PathVariable Long professionalId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return listAvailableTimeSlotsUseCase.execute(professionalId, date);
    }

    @GetMapping("/professional/{professionalId}/available-days")
    @Operation(summary = "Listar dias disponíveis para agendamento", description = "Retorna os dias que o profissional tem horários disponíveis em um intervalo de datas")
    public List<LocalDate> getAvailableDays(
            @PathVariable Long professionalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        return listAvailableDaysUseCase.execute(professionalId, start, end);
    }

    @GetMapping("/professional/{professionalId}/available-slots-by-day")
    @Operation(summary = "Listar horários disponíveis por dia", description = "Retorna os horários disponíveis por dia em um intervalo de datas"
    )
    public Map<LocalDate, List<LocalTime>> getAvailableSlotsByDay(
            @PathVariable Long professionalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return listAvailableSlotsByDayUseCase.execute(professionalId, start, end);
    }
}
