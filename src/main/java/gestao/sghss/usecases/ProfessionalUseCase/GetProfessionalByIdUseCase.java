package gestao.sghss.usecases.ProfessionalUseCase;

import gestao.sghss.domain.Professional;
import gestao.sghss.gateways.ProfessionalGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetProfessionalByIdUseCase {

    private final ProfessionalGateway professionalGateway;

    public Professional execute(Long id) {
        log.info("Buscando profissional pelo ID: {}", id);
        Professional professional = professionalGateway.findById(id);
        log.info("Profissional encontrado: ID={} | Nome={}", professional.getId(), professional.getName());
        return professional;
    }
}
