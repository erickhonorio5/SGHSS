package gestao.sghss.repositories;

import gestao.sghss.gateways.entities.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByCpf(String cpf);

    Page<PatientEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<PatientEntity> findByActiveTrue(Pageable pageable);

    boolean existsByCpf(String cpf);
    boolean existsById(Long id);

    Optional<PatientEntity> findByEmailIgnoreCase(final String email);

    boolean existsByEmailIgnoreCase(final String email);

    @Modifying
    @Query("UPDATE PatientEntity p SET p.active = false WHERE p.id = :id")
    void deactivateById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE PatientEntity p SET p.active = true WHERE p.id = :id")
    void activateById(@Param("id") Long id);
}
