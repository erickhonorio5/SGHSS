package gestao.sghss.repositories;

import gestao.sghss.gateways.entities.ProfessionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessionalRepository extends JpaRepository<ProfessionalEntity, Long> {

    @Query("SELECT p FROM ProfessionalEntity p JOIN p.specialties ps WHERE ps.specialty.id = :specialtyId")
    List<ProfessionalEntity> findBySpecialtyId(@Param("specialtyId") Long specialtyId);

}
