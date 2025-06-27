package gestao.sghss.usecases.WorkScheduleUseCase;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.domain.validator.WorkScheduleValidator;
import gestao.sghss.gateways.ProfessionalGateway;
import gestao.sghss.gateways.WorkScheduleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PatchWorkScheduleUseCase {

    private final WorkScheduleGateway gateway;
    private final ProfessionalGateway professionalGateway;
    private final WorkScheduleValidator validator;

    public WorkSchedule execute(Long professionalId, Long scheduleId, WorkSchedule patch) {
        log.info("Patch de horário ID: {} para profissional ID: {}", scheduleId, professionalId);

        professionalGateway.findById(professionalId);

        WorkSchedule existing = gateway.findById(scheduleId);

        validator.validateOwnership(professionalId, existing);

        if (isDayOfWeekBeingUpdated(patch, existing)) {
            validator.validateNoDuplicateDay(patch.getDayOfWeek(), professionalId, scheduleId, gateway.findByProfessionalId(professionalId));
            existing.setDayOfWeek(patch.getDayOfWeek());
            log.info("Dia da semana atualizado para {}", patch.getDayOfWeek());
        }

        updateTimesIfPresent(existing, patch);

        validator.validateTimes(
                existing.getStartTime(),
                existing.getEndTime(),
                existing.getLunchStartTime(),
                existing.getLunchEndTime()
        );

        WorkSchedule saved = gateway.save(existing);
        log.info("Horário atualizado via PATCH com sucesso: {}", saved);
        return saved;
    }

    private boolean isDayOfWeekBeingUpdated(WorkSchedule patch, WorkSchedule existing) {
        return patch.getDayOfWeek() != null && !patch.getDayOfWeek().equals(existing.getDayOfWeek());
    }

    private void updateTimesIfPresent(WorkSchedule existing, WorkSchedule patch) {
        if (patch.getStartTime() != null) existing.setStartTime(patch.getStartTime());
        if (patch.getEndTime() != null) existing.setEndTime(patch.getEndTime());
        if (patch.getLunchStartTime() != null) existing.setLunchStartTime(patch.getLunchStartTime());
        if (patch.getLunchEndTime() != null) existing.setLunchEndTime(patch.getLunchEndTime());
    }
}


