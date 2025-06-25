package gestao.sghss.gateways;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.gateways.mapper.WorkScheduleMapper;
import gestao.sghss.repositories.WorkScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkScheduleGateway {

    private final WorkScheduleRepository repository;
    private final WorkScheduleMapper mapper;

    public WorkSchedule save (final WorkSchedule workSchedule){
        return mapper.toDomain(repository.save(mapper.toEntity(workSchedule)));
    }

    public Optional<WorkSchedule> findById (final Long id){
        return repository.findById(id).map(mapper::toDomain);
    }

    public List<WorkSchedule> findByProfessionalId(final Long professionalId){
        return repository.findAllByProfessionalId(professionalId).stream().map(mapper::toDomain).toList();
    }

    public void deleteById (final Long id){
        repository.deleteById(id);
    }
}
