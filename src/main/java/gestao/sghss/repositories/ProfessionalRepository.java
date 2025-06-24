package gestao.sghss.repositories;

import gestao.sghss.gateways.entities.ProfessionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalRepository extends JpaRepository<ProfessionalEntity, Long> {
}
