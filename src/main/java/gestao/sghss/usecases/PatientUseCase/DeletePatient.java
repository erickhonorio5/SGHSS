package gestao.sghss.usecases.PatientUseCase;

import gestao.sghss.exceptions.PatientNotFoundException;
import gestao.sghss.gateways.PatientGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeletePatient {

    private final PatientGateway patientGateway;

    public void execute(Long id) {
        log.info("Deleting patient with id: {}", id);
        if (!patientGateway.existsById(id)) {
            throw new PatientNotFoundException();
        }
        patientGateway.delete(id);
    }
}
