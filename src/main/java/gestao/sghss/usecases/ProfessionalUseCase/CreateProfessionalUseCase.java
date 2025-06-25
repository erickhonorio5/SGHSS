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
public class CreateProfessionalUseCase {

    private final ProfessionalGateway professionalGateway;
    private final SpecialtyGateway specialtyGateway;

    public Professional execute(Professional partialProfessional, List<Long> specialtyIds) {
        log.info("Iniciando criação de profissional: {}", partialProfessional.getName());

        List<Specialty> specialties = specialtyGateway.findAll().stream()
                .filter(s -> specialtyIds.contains(s.getId()))
                .toList();

        if (specialties.size() != specialtyIds.size()) {
            log.warn("Especialidades inválidas: recebidas={} | encontradas={}", specialtyIds.size(), specialties.size());
            throw new SpecialtyException();
        }

        partialProfessional.setSpecialties(specialties);
        partialProfessional.setActive(true);

        log.info("Salvando profissional com {} especialidade(s)...", specialties.size());
        Professional savedProfessional = professionalGateway.save(partialProfessional);

        log.info("Profissional criado com sucesso! ID: {}", savedProfessional.getId());
        return savedProfessional;
    }
}
