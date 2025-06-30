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
public class ConfirmAppointmentUseCase {

    private final AppointmentGateway appointmentGateway;

    public Appointment execute(Long appointmentId) {
        log.info("Confirmando consulta com ID {}", appointmentId);

        Appointment appointment = appointmentGateway.findById(appointmentId);

        if (!appointment.canConfirm()) throw new InvalidAppointmentException("Consulta não pode ser confirmada no estado atual: " + appointment.getAppointmentStatus());

        appointment.confirm();

        Appointment updated = appointmentGateway.save(appointment);
        log.info("Consulta confirmada com sucesso, ID: {}", updated.getAppointmentId());
        return updated;
    }
}
