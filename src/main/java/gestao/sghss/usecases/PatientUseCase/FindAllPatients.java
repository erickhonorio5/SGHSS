package gestao.sghss.usecases.PatientUseCase;

import gestao.sghss.domain.Patient;
import gestao.sghss.gateways.PatientGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindAllPatients {

    private final PatientGateway patientGateway;

    public Page<Patient> execute(String name, Boolean active, Pageable pageable) {
        log.info("Executing patient search with parameters: name='{}', active='{}', pageable='{}'", name, active, pageable);

        if (name != null && !name.isBlank()) {
            log.debug("Searching patients by name containing '{}'", name);
            return patientGateway.findByName(name, pageable);
        }

        if (Boolean.TRUE.equals(active)) {
            log.debug("Searching active patients only");
            return patientGateway.findAllActive(pageable);
        }

        return patientGateway.findAll(pageable);
    }
}