package gestao.sghss.usecases.AppointmentUseCase;

import gestao.sghss.domain.Appointment;
import gestao.sghss.exceptions.AppointmentValidationException;
import gestao.sghss.gateways.AppointmentGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListAppointmentsByProfessionalIdUseCase {

    private final AppointmentGateway appointmentGateway;

    public List<Appointment> execute(Long professionalId) {
        log.info("Listando agendamentos do profissional ID: {}", professionalId);

        var appointments = appointmentGateway.findByProfessionalId(professionalId);

        if (appointments.isEmpty()) throw new AppointmentValidationException("Nenhuma consulta encontrada para o profissional com ID: " + professionalId);

        return appointments;
    }
}