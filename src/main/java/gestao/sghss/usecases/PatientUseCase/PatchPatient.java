package gestao.sghss.usecases.PatientUseCase;

import gestao.sghss.domain.Patient;
import gestao.sghss.exceptions.BusinessException;
import gestao.sghss.exceptions.EntityConflictException;
import gestao.sghss.exceptions.PatientNotFoundException;
import gestao.sghss.gateways.PatientGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PatchPatient {

    private final PatientGateway patientGateway;

    @Transactional
    public Patient execute(Long id, Patient updatedData) {
        log.info("Patching patient with id {}", id);

        Patient existing = patientGateway.findById(id);
        if (existing == null) {
            throw new PatientNotFoundException();
        }

        if (updatedData.getName() != null) {
            existing.setName(updatedData.getName());
        }

        if (updatedData.getEmail() != null && !updatedData.getEmail().equalsIgnoreCase(existing.getEmail())) {
            if (patientGateway.existsByEmail(updatedData.getEmail())) {
                throw new EntityConflictException("Email already registered");
            }
            existing.setEmail(updatedData.getEmail());
        }

        if (updatedData.getPhone() != null) {
            existing.setPhone(updatedData.getPhone());
        }

        if (updatedData.getBirthDate() != null) {
            existing.setBirthDate(updatedData.getBirthDate());
        }

        if (updatedData.getAddress() != null) {
            var newAddress = updatedData.getAddress();
            if (existing.getAddress() == null) {
                existing.setAddress(newAddress);
            } else {
                if (newAddress.getZipCode() != null)
                    existing.getAddress().setZipCode(newAddress.getZipCode());
                if (newAddress.getStreet() != null)
                    existing.getAddress().setStreet(newAddress.getStreet());
                if (newAddress.getNumber() != null)
                    existing.getAddress().setNumber(newAddress.getNumber());
                if (newAddress.getComplement() != null)
                    existing.getAddress().setComplement(newAddress.getComplement());
                if (newAddress.getNeighborhood() != null)
                    existing.getAddress().setNeighborhood(newAddress.getNeighborhood());
                if (newAddress.getCity() != null)
                    existing.getAddress().setCity(newAddress.getCity());
                if (newAddress.getState() != null)
                    existing.getAddress().setState(newAddress.getState());
            }
        }

        if (updatedData.getInsurance() != null) {
            var newInsurance = updatedData.getInsurance();
            if (existing.getInsurance() == null) {
                existing.setInsurance(newInsurance);
            } else {
                if (newInsurance.getName() != null)
                    existing.getInsurance().setName(newInsurance.getName());
                if (newInsurance.getNumber() != null)
                    existing.getInsurance().setNumber(newInsurance.getNumber());
                if (newInsurance.getExpiryDate() != null)
                    existing.getInsurance().setExpiryDate(newInsurance.getExpiryDate());
            }
        }

        return patientGateway.update(existing);
    }
}


