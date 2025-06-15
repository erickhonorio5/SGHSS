package gestao.sghss.usecases.PatientUseCase;

import gestao.sghss.domain.Patient;
import gestao.sghss.domain.validator.CPFValidator;
import gestao.sghss.exceptions.BusinessException;
import gestao.sghss.gateways.PatientGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatePatient {

    private final PatientGateway patientGateway;

    public Patient execute(final Patient patient) {
        log.info("[create-patient] Creating patient '{}'", patient.getName());

        validate(patient);

        return patientGateway.save(patient);
    }

    private void validate(final Patient patient) {
        requireNonEmpty(patient.getCpf(), "CPF is required");
        requireNonEmpty(patient.getName(), "Name is required");

//        if (!CPFValidator.isValid(patient.getCpf())) {
//            throw new BusinessException("Invalid CPF");
//        }

        if (patientGateway.existsByCpf(patient.getCpf())) {
            throw new BusinessException("Patient with this CPF already exists");
        }

        if (hasValue(patient.getEmail()) && patientGateway.existsByEmail(patient.getEmail())) {
            throw new BusinessException("Patient with this email already exists");
        }

        if (patient.getAddress() == null) {
            throw new BusinessException("Address information is required");
        }

        if (patient.getInsurance() == null) {
            throw new BusinessException("Insurance information is required");
        }
    }

    private void requireNonEmpty(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new BusinessException(message);
        }
    }

    private boolean hasValue(String value) {
        return value != null && !value.isBlank();
    }
}

