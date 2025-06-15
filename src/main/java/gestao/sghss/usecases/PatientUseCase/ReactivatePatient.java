package gestao.sghss.usecases.PatientUseCase;

import gestao.sghss.exceptions.InvalidPatientStatusException;
import gestao.sghss.exceptions.PatientNotFoundException;
import gestao.sghss.gateways.PatientGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReactivatePatient {

    private final PatientGateway patientGateway;

    @Transactional
    public void execute(Long id) {
        log.info("Attempting to activate patient with ID: {}", id);

        var patient = patientGateway.findById(id);

        if (patient.isActive()) {
            log.warn("Patient with ID {} is already active.", id);
            throw new InvalidPatientStatusException("Patient is already active.");
        }

        patientGateway.activate(id);
        log.info("Patient with ID {} successfully activated.", id);
    }
}
