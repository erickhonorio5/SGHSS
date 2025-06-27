package gestao.sghss.usecases.WorkScheduleUseCase;

import gestao.sghss.exceptions.WorkScheduleException;
import gestao.sghss.exceptions.WorkScheduleOwnershipException;
import gestao.sghss.gateways.WorkScheduleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteWorkScheduleUseCase {

    private final WorkScheduleGateway workScheduleGateway;

    public void execute(Long professionalId, Long scheduleId) {
        log.info("Tentando excluir horário ID: {} do profissional ID: {}", scheduleId, professionalId);

        var schedule = workScheduleGateway.findById(scheduleId);

        if (!Objects.equals(schedule.getProfessionalId(), professionalId)) throw new WorkScheduleOwnershipException();

        workScheduleGateway.deleteById(scheduleId);
        log.info("Horário de trabalho ID {} removido com sucesso", scheduleId);
    }
}


