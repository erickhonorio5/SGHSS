package gestao.sghss.usecases.ProfessionalUseCase;

import gestao.sghss.domain.Professional;
import gestao.sghss.gateways.ProfessionalGateway;
import gestao.sghss.gateways.SpecialtyGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListProfessionalsBySpecialtyUseCase {

    private final SpecialtyGateway specialtyGateway;
    private final ProfessionalGateway professionalGateway;

    public List<Professional> execute(Long specialtyId) {
        log.info("Buscando profissionais pela especialidade ID: {}", specialtyId);

        specialtyGateway.findById(specialtyId);

        List<Professional> professionals = professionalGateway.findBySpecialtyId(specialtyId);
        log.info("Foram encontrados {} profissionais para a especialidade ID {}", professionals.size(), specialtyId);

        return professionals;
    }
}

