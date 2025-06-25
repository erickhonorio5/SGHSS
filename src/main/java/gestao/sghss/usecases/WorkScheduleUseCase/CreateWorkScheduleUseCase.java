package gestao.sghss.usecases.WorkScheduleUseCase;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.gateways.ProfessionalGateway;
import gestao.sghss.gateways.WorkScheduleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateWorkScheduleUseCase {

    private final ProfessionalGateway professionalGateway;
    private final WorkScheduleGateway workScheduleGateway;

    public WorkSchedule execute(Long professionalId, WorkSchedule schedule) {
        log.info("Criando horário de trabalho para profissional ID: {}", professionalId);

        professionalGateway.findById(professionalId);

        schedule.setProfessionalId(professionalId);

        WorkSchedule created = workScheduleGateway.save(schedule);

        log.info("Horário criado com sucesso para {}: {} - {}",
                created.getDayOfWeek(), created.getStartTime(), created.getEndTime());

        return created;
    }
}
