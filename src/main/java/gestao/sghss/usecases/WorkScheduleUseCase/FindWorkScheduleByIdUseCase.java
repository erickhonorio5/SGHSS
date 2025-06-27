package gestao.sghss.usecases.WorkScheduleUseCase;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.exceptions.WorkScheduleOwnershipException;
import gestao.sghss.gateways.ProfessionalGateway;
import gestao.sghss.gateways.WorkScheduleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindWorkScheduleByIdUseCase {

    private final WorkScheduleGateway workScheduleGateway;
    private final ProfessionalGateway professionalGateway;

    public WorkSchedule execute(Long professionalId, Long scheduleId) {
        log.info("Buscando horário ID {} para profissional ID {}", scheduleId, professionalId);

        professionalGateway.findById(professionalId);
        WorkSchedule schedule = workScheduleGateway.findById(scheduleId);

        if (!schedule.getProfessionalId().equals(professionalId)) throw new WorkScheduleOwnershipException();

        return schedule;
    }
}
