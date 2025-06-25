package gestao.sghss.usecases.ProfessionalUseCase;

import gestao.sghss.domain.Professional;
import gestao.sghss.domain.Specialty;
import gestao.sghss.exceptions.SpecialtyException;
import gestao.sghss.gateways.ProfessionalGateway;
import gestao.sghss.gateways.SpecialtyGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateProfessionalUseCase {

    private final ProfessionalGateway professionalGateway;
    private final SpecialtyGateway specialtyGateway;

    public Professional execute(Professional professional, List<Long> specialtyIds) {
        log.info("Atualizando profissional ID: {}", professional.getId());

        professionalGateway.findById(professional.getId());

        List<Specialty> specialties = specialtyGateway.findAll().stream()
                .filter(s -> specialtyIds.contains(s.getId()))
                .toList();

        if (specialties.size() != specialtyIds.size()) {
            log.warn("Especialidades inválidas: esperadas={} | encontradas={}",
                    specialtyIds.size(), specialties.size());
            throw new SpecialtyException();
        }

        professional.setSpecialties(specialties);

        Professional updated = professionalGateway.save(professional);
        log.info("Profissional atualizado com sucesso ID: {}", updated.getId());

        return updated;
    }
}