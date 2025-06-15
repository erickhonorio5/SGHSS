package gestao.sghss.usecases.PatientUseCase;

import gestao.sghss.domain.Patient;
import gestao.sghss.gateways.PatientGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindPatientByCpf {

    private final PatientGateway patientGateway;

    public Patient execute(String cpf) {
        log.info("Searching for patient with CPF: {}", cpf);
        return patientGateway.findByCpf(cpf);
    }
}
