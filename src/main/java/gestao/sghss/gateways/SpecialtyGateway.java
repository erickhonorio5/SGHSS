package gestao.sghss.gateways;

import gestao.sghss.domain.Specialty;
import gestao.sghss.exceptions.SpecialtyException;
import gestao.sghss.gateways.mapper.SpecialtyMapper;
import gestao.sghss.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpecialtyGateway {

    private final SpecialtyRepository repository;
    private final SpecialtyMapper mapper;

    public Specialty save(final Specialty specialty) {
        return mapper.toDomain(repository.save(mapper.toEntity(specialty)));
    }

    public Specialty findById(final Long id) {
        return repository.findById(id).map(mapper::toDomain).orElseThrow(SpecialtyException::new);
    }

    public List<Specialty> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    public void deleteById(final Long id) {
        repository.deleteById(id);
    }
}
