package gestao.sghss.usecases.AppointmentUseCase;

import gestao.sghss.domain.Appointment;
import gestao.sghss.exceptions.InvalidAppointmentException;
import gestao.sghss.gateways.AppointmentGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompleteAppointmentUseCase {

    private final AppointmentGateway appointmentGateway;

    public Appointment execute(Long appointmentId) {
        log.info("Finalizando consulta com ID {}", appointmentId);

        Appointment appointment = appointmentGateway.findById(appointmentId);

        if (!appointment.canComplete()) throw new InvalidAppointmentException("Consulta não pode ser finalizada no estado atual: " + appointment.getAppointmentStatus());

        appointment.complete();

        Appointment updated = appointmentGateway.save(appointment);
        log.info("Consulta finalizada com sucesso, ID: {}", updated.getAppointmentId());
        return updated;
    }
}
