package gestao.sghss.usecases.WorkScheduleUseCase;

import gestao.sghss.exceptions.WorkScheduleException;
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

        var schedule = workScheduleGateway.findById(scheduleId)
                .orElseThrow(() -> {
                    log.warn("Horário de trabalho ID {} não encontrado", scheduleId);
                    return new WorkScheduleException("Horário de trabalho não encontrado");
                });

        if (!Objects.equals(schedule.getProfessionalId(), professionalId)) {
            log.warn("Horário ID {} não pertence ao profissional ID {}", scheduleId, professionalId);
            throw new WorkScheduleException("O horário não pertence ao profissional informado");
        }

        workScheduleGateway.deleteById(scheduleId);
        log.info("Horário de trabalho ID {} removido com sucesso", scheduleId);
    }
}


