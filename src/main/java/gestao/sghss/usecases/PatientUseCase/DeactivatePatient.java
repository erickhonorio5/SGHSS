package gestao.sghss.usecases.PatientUseCase;

import gestao.sghss.exceptions.InvalidPatientStatusException;
import gestao.sghss.gateways.PatientGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeactivatePatient {

    private final PatientGateway patientGateway;

    @Transactional
    public void execute(Long id) {
        log.info("Attempting to deactivate patient with ID: {}", id);

        var patient = patientGateway.findById(id);

        if (!patient.isActive()) {
            log.warn("Patient with ID {} is already deactivated.", id);
            throw new InvalidPatientStatusException("Patient is already deactivated.");
        }

        patientGateway.deactivate(id);
        log.info("Patient with ID {} successfully deactivated.", id);
    }
}
