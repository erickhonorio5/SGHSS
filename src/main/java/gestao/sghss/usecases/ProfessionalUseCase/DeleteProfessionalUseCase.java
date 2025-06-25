package gestao.sghss.usecases.ProfessionalUseCase;

import gestao.sghss.domain.Professional;
import gestao.sghss.gateways.ProfessionalGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteProfessionalUseCase {

    private final ProfessionalGateway professionalGateway;

    public void execute(Long id) {
        log.info("Iniciando exclusão do profissional ID: {}", id);

        Professional existing = professionalGateway.findById(id);

        professionalGateway.deleteById(existing.getId());

        log.info("Profissional ID {} excluído com sucesso", id);
    }
}
