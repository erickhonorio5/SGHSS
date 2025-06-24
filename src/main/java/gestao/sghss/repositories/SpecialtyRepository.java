package gestao.sghss.repositories;

import gestao.sghss.gateways.entities.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Long> {
}
