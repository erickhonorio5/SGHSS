package gestao.sghss.usecases.AppointmentUseCase;

import gestao.sghss.domain.Appointment;
import gestao.sghss.domain.validator.AppointmentValidator;
import gestao.sghss.exceptions.InvalidAppointmentException;
import gestao.sghss.gateways.AppointmentGateway;
import gestao.sghss.services.AvailabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class RescheduleAppointmentUseCase {

    private final AppointmentGateway appointmentGateway;
    private final AppointmentValidator appointmentValidator;
    private final AvailabilityService service;

    public Appointment execute(Long appointmentId, LocalDate newDate, LocalTime newTime) {
        log.info("Reagendando consulta ID {} para {} às {}", appointmentId, newDate, newTime);

        appointmentValidator.validateAppointmentDateTime(newDate, newTime);

        Appointment appointment = appointmentGateway.findById(appointmentId);

        if (!appointment.canReschedule()) throw new InvalidAppointmentException("Consulta não pode ser reagendada no estado atual: " + appointment.getAppointmentStatus());

        service.validateAvailability(appointment.getProfessionalId(), newDate, newTime);

        appointment.reschedule(newDate, newTime);
        Appointment updated = appointmentGateway.save(appointment);

        log.info("Consulta reagendada com sucesso, novo horário: {} às {}", updated.getAppointmentDate(), updated.getAppointmentTime());
        return updated;
    }
}
