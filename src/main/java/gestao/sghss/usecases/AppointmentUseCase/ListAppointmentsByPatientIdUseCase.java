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
public class ListAppointmentsByPatientIdUseCase {

    private final AppointmentGateway appointmentGateway;

    public List<Appointment> execute(Long patientId) {
        log.info("Listando consultas do paciente com ID {}", patientId);

        var patient = appointmentGateway.findByPatientId(patientId);

        if (patient.isEmpty()) {
            log.info("Nenhuma consulta encontrada para o paciente com ID: {}", patientId);
            throw new AppointmentValidationException("Nenhuma consulta encontrada para o paciente com ID: " + patientId);
        }

        return patient;
    }
}
