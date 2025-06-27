package gestao.sghss.usecases.WorkScheduleUseCase;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.gateways.WorkScheduleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListWorkSchedulesByProfessionalUseCase {

    private final WorkScheduleGateway workScheduleGateway;

    public List<WorkSchedule> execute(Long professionalId) {
        log.info("Listando horários do profissional ID: {}", professionalId);
        return workScheduleGateway.findByProfessionalId(professionalId).stream()
                .sorted(Comparator.comparing(ws -> ws.getDayOfWeek().getValue())) // Ordena de 1 (Monday) a 7 (Sunday)
                .toList();
    }
}
