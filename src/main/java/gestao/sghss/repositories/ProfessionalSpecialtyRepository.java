package gestao.sghss.repositories;

import gestao.sghss.gateways.entities.ProfessionalEntity;
import gestao.sghss.gateways.entities.ProfessionalSpecialtyEntity;
import gestao.sghss.gateways.entities.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessionalSpecialtyRepository extends JpaRepository<ProfessionalSpecialtyEntity, Long> {

    List<ProfessionalSpecialtyEntity> findAllByProfessional(ProfessionalEntity professional);

    Optional<ProfessionalSpecialtyEntity> findByProfessionalAndSpecialty(ProfessionalEntity professional, SpecialtyEntity specialty);

    void deleteAllByProfessional(ProfessionalEntity professional);
}