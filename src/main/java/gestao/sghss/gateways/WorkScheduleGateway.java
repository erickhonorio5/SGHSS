package gestao.sghss.gateways;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.exceptions.WorkScheduleException;
import gestao.sghss.exceptions.WorkScheduleNotFoundException;
import gestao.sghss.gateways.mapper.WorkScheduleMapper;
import gestao.sghss.repositories.WorkScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkScheduleGateway {

    private final WorkScheduleRepository repository;

    @Qualifier("workScheduleMapper")
    private final WorkScheduleMapper mapper;

    public WorkSchedule save (final WorkSchedule workSchedule){
        return mapper.toDomain(repository.save(mapper.toEntity(workSchedule)));
    }

    public List<WorkSchedule> saveAll(List<WorkSchedule> schedules) {
        var entities = schedules.stream()
                .map(mapper::toEntity)
                .toList();

        var savedEntities = repository.saveAll(entities);

        return savedEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    public WorkSchedule findById (final Long id){
        return repository.findById(id).map(mapper::toDomain).orElseThrow(() -> new WorkScheduleNotFoundException(id));
    }

    public List<WorkSchedule> findByProfessionalId(final Long professionalId){
        return repository.findAllByProfessionalId(professionalId).stream().map(mapper::toDomain).toList();
    }

    public void deleteById (final Long id){
        repository.deleteById(id);
    }

    public boolean existsByProfessionalIdAndDayOfWeek(Long professionalId, DayOfWeek dayOfWeek) {
        return repository.existsByProfessional_IdAndDayOfWeek(professionalId, dayOfWeek);
    }
}
