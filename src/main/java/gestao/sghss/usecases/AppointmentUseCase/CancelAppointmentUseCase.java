package gestao.sghss.usecases.AppointmentUseCase;

import gestao.sghss.domain.Appointment;
import gestao.sghss.exceptions.InvalidPatientStatusException;
import gestao.sghss.gateways.AppointmentGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CancelAppointmentUseCase {

    private final AppointmentGateway appointmentGateway;

    public Appointment execute(Long appointmentId) {
        log.info("Cancelando agendamento ID: {}", appointmentId);

        Appointment appointment = appointmentGateway.findById(appointmentId);

        if(!appointment.canCancel()) throw new InvalidPatientStatusException("Não é possível cancelar uma consulta já finalizada.");

        appointment.cancel();
        Appointment cancelled = appointmentGateway.save(appointment);

        log.info("Agendamento ID {} cancelado com sucesso", appointmentId);
        return cancelled;
    }
}