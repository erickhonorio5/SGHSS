package gestao.sghss.usecases.SpecialityUseCase;

import gestao.sghss.domain.Specialty;
import gestao.sghss.gateways.SpecialtyGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SpecialityUseCase {

    private final SpecialtyGateway specialtyGateway;

    public List<Specialty> listAll() {
        log.info("Buscando todas as especialidades");
        return specialtyGateway.findAll();
    }

    public Specialty findWithId(Long id) {
        log.info("Buscando especialidade com id: {}", id);
        return specialtyGateway.findById(id);
    }

    public void delete(Long id) {
        log.info("Deletando especialidade com id: {}", id);
        specialtyGateway.deleteById(id);
    }
}
