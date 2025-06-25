package gestao.sghss.usecases.ProfessionalUseCase;

import gestao.sghss.domain.Professional;
import gestao.sghss.gateways.ProfessionalGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListProfessionalsUseCase {

    private final ProfessionalGateway professionalGateway;

    public List<Professional> execute() {
        log.info("Listando todos os profissionais");
        List<Professional> professionals = professionalGateway.findAll();
        log.info("Foram encontrados {} profissionais", professionals.size());
        return professionals;
    }
}
