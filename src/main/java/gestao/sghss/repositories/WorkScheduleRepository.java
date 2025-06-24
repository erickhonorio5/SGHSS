package gestao.sghss.repositories;

import gestao.sghss.gateways.entities.WorkScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkScheduleRepository extends JpaRepository<WorkScheduleEntity, Long> {

    List<WorkScheduleEntity> findAllByProfessionalId(Long professionalId);
}
