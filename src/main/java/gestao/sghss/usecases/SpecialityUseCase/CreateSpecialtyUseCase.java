package gestao.sghss.usecases.SpecialityUseCase;

import gestao.sghss.domain.Specialty;
import gestao.sghss.gateways.SpecialtyGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateSpecialtyUseCase {

    private final SpecialtyGateway specialtyGateway;

    public Specialty execute(Specialty specialty) {
        log.info("Criando especialidade: {}", specialty.getName());
        validate(specialty);
        return specialtyGateway.save(specialty);
    }

    private void validate(Specialty specialty) {
        if (specialty.getName() == null || specialty.getName().isBlank()) throw new IllegalArgumentException("Nome da especialidade é obrigatório.");
    }
}
