package gestao.sghss.usecases.AppointmentUseCase;

import gestao.sghss.domain.Appointment;
import gestao.sghss.gateways.AppointmentGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetAppointmentByIdUseCase {

    private final AppointmentGateway appointmentGateway;

    public Appointment execute(Long appointmentId) {
        log.info("Buscando agendamento pelo ID: {}", appointmentId);
        Appointment appointment = appointmentGateway.findById(appointmentId);
        log.info("Agendamento encontrado: ID={} | Data={}", appointment.getAppointmentId(), appointment.getAppointmentDate());
        return appointment;
    }
}
