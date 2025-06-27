package gestao.sghss.usecases.WorkScheduleUseCase;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.domain.validator.WorkScheduleValidator;
import gestao.sghss.gateways.ProfessionalGateway;
import gestao.sghss.gateways.WorkScheduleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateWorkScheduleUseCase {

    private final WorkScheduleGateway workScheduleGateway;
    private final ProfessionalGateway professionalGateway;
    private final WorkScheduleValidator validator;

    public WorkSchedule execute(Long professionalId, Long scheduleId, WorkSchedule updatedSchedule) {
        log.info("Atualizando horário de trabalho ID: {} para profissional ID: {}", scheduleId, professionalId);

        professionalGateway.findById(professionalId);

        WorkSchedule existing = workScheduleGateway.findById(scheduleId);

        validator.validateOwnership(professionalId, existing);
        validator.validateNoDuplicateDay(
                updatedSchedule.getDayOfWeek(),
                professionalId,
                scheduleId,
                workScheduleGateway.findByProfessionalId(professionalId)
        );
        validator.validateTimes(
                updatedSchedule.getStartTime(),
                updatedSchedule.getEndTime(),
                updatedSchedule.getLunchStartTime(),
                updatedSchedule.getLunchEndTime()
        );

        updateFields(existing, updatedSchedule);

        WorkSchedule saved = workScheduleGateway.save(existing);
        log.info("Horário atualizado com sucesso: {}", saved);
        return saved;
    }

    private void updateFields(WorkSchedule existing, WorkSchedule updated) {
        existing.setDayOfWeek(updated.getDayOfWeek());
        existing.setStartTime(updated.getStartTime());
        existing.setEndTime(updated.getEndTime());
        existing.setLunchStartTime(updated.getLunchStartTime());
        existing.setLunchEndTime(updated.getLunchEndTime());
    }
}


