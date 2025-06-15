package gestao.sghss.usecases.PatientUseCase;

import gestao.sghss.domain.Patient;
import gestao.sghss.gateways.PatientGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindPatientById {

    private final PatientGateway patientGateway;

    public Patient execute(Long id) {
        log.info("Searching for patient with id: {}", id);
        return patientGateway.findById(id);
    }
}
