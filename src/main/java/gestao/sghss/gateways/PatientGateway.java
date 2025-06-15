package gestao.sghss.gateways;

import gestao.sghss.domain.Patient;
import gestao.sghss.exceptions.PatientNotFoundException;
import gestao.sghss.gateways.mapper.PatientMapper;
import gestao.sghss.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientGateway {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public Patient save (final Patient patient){
        return patientMapper.toDomain(patientRepository.save(patientMapper.toEntity(patient)));
    }

    public Page<Patient> findAll (Pageable pageable){
        return patientRepository.findAll(pageable).map(patientMapper::toDomain);
    }

    public Page<Patient> findAllActive(Pageable pageable) {
        return patientRepository.findByActiveTrue(pageable).map(patientMapper::toDomain);
    }

    public Page<Patient> findByName (String name, Pageable pageable){
        return patientRepository.findByNameContainingIgnoreCase(name, pageable).map(patientMapper::toDomain);
    }

    public Patient findById (final Long id){
        return patientMapper.toDomain(patientRepository.findById(id).orElseThrow(PatientNotFoundException::new));
    }

    public Patient findByCpf (final String cpf){
        return patientMapper.toDomain(patientRepository.findByCpf(cpf).orElseThrow(PatientNotFoundException::new));
    }


    public Patient findByEmail(final String email) {
        return patientRepository.findByEmailIgnoreCase(email).map(patientMapper::toDomain).orElseThrow(PatientNotFoundException::new);
    }

    public boolean existsByEmail(final String email) {
        return patientRepository.existsByEmailIgnoreCase(email);
    }

    public Patient update (final Patient patient){
        return patientMapper.toDomain(patientRepository.save(patientMapper.toEntity(patient)));
    }

    public void delete (final Long id){
        patientRepository.deleteById(id);
    }

    public boolean existsByCpf (final String cpf){
        return patientRepository.existsByCpf(cpf);
    }

    public boolean existsById (final Long id){
        return patientRepository.existsById(id);
    }

    public void deactivate (final Long id) {
        patientRepository.deactivateById(id);
    }

    public void activate (final Long id){
        patientRepository.activateById(id);
    }
}
