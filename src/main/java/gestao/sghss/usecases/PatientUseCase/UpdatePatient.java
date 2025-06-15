package gestao.sghss.usecases.PatientUseCase;

import gestao.sghss.domain.Patient;
import gestao.sghss.exceptions.BusinessException;
import gestao.sghss.gateways.PatientGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdatePatient {

    private final PatientGateway patientGateway;

    @Transactional
    public Patient execute(Long id, Patient updatedData) {
        log.info("Updating patient with id {}", id);

        Patient existing = patientGateway.findById(id);

        existing.setName(updatedData.getName());
        existing.setEmail(updatedData.getEmail());
        existing.setPhone(updatedData.getPhone());
        existing.setBirthDate(updatedData.getBirthDate());

        if (updatedData.getAddress() != null) {
            var newAddress = updatedData.getAddress();
            if (existing.getAddress() == null) {
                existing.setAddress(newAddress);
            } else {
                existing.getAddress().setZipCode(newAddress.getZipCode());
                existing.getAddress().setStreet(newAddress.getStreet());
                existing.getAddress().setNumber(newAddress.getNumber());
                existing.getAddress().setComplement(newAddress.getComplement());
                existing.getAddress().setNeighborhood(newAddress.getNeighborhood());
                existing.getAddress().setCity(newAddress.getCity());
                existing.getAddress().setState(newAddress.getState());
            }
        }

        if (updatedData.getInsurance() != null) {
            var newInsurance = updatedData.getInsurance();
            if (existing.getInsurance() == null) {
                existing.setInsurance(newInsurance);
            } else {
                existing.getInsurance().setName(newInsurance.getName());
                existing.getInsurance().setNumber(newInsurance.getNumber());
                existing.getInsurance().setExpiryDate(newInsurance.getExpiryDate());
            }
        }

        if (updatedData.getEmail() != null && !updatedData.getEmail().equalsIgnoreCase(existing.getEmail())) {
            if (patientGateway.existsByEmail(updatedData.getEmail())) {
                throw new BusinessException("Email already registered");
            }
        }

        return patientGateway.update(existing);
    }
}

