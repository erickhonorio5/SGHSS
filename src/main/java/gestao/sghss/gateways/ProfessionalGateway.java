package gestao.sghss.gateways;

import gestao.sghss.domain.Professional;
import gestao.sghss.exceptions.EntityNotFoundException;
import gestao.sghss.gateways.mapper.ProfessionalMapper;
import gestao.sghss.repositories.ProfessionalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfessionalGateway {

    private final ProfessionalRepository repository;
    private final ProfessionalMapper mapper;

    public Professional save(Professional professional) {
        var entity = mapper.toEntity(professional);

        if (entity.getSpecialties() != null) {
            entity.getSpecialties()
                    .forEach(ps -> ps.setProfessional(entity));
        }

        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    public Professional findById(final Long id) {
        return repository.findById(id).map(mapper::toDomain).orElseThrow(EntityNotFoundException::new);
    }

    public List<Professional> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    public List<Professional> findBySpecialtyId(final Long specialtyId) {
        return repository.findBySpecialtyId(specialtyId).stream().map(mapper::toDomain).toList();
    }

    public void deleteById(final Long id) {
        repository.deleteById(id);
    }
}

