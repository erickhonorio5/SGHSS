package gestao.sghss.usecases.WorkScheduleUseCase;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.exceptions.WorkScheduleConflictException;
import gestao.sghss.exceptions.WorkScheduleOwnershipException;
import gestao.sghss.gateways.ProfessionalGateway;
import gestao.sghss.gateways.WorkScheduleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Slf4j
@RequiredArgsConstructor
@Component
public class PatchWorkScheduleUseCase {

    private final WorkScheduleGateway gateway;
    private final ProfessionalGateway professionalGateway;

    public WorkSchedule execute(Long professionalId, Long scheduleId, WorkSchedule patch) {
        log.info("Patch de horário de trabalho ID: {} para profissional ID: {}", scheduleId, professionalId);

        validateProfessionalExists(professionalId);

        var existing = findExistingSchedule(scheduleId);

        validateOwnership(professionalId, existing);

        if (isDayOfWeekBeingUpdated(patch, existing)) {
            validateConflict(professionalId, scheduleId, patch.getDayOfWeek());
            existing.setDayOfWeek(patch.getDayOfWeek());
            log.info("Dia da semana alterado para {}", patch.getDayOfWeek());
        }

        updateTimes(existing, patch);

        var saved = gateway.save(existing);
        log.info("Horário atualizado via patch: {}", saved);
        return saved;
    }

    private void validateProfessionalExists(Long professionalId) {
        professionalGateway.findById(professionalId);
    }

    private WorkSchedule findExistingSchedule(Long scheduleId) {
        return gateway.findById(scheduleId);
    }

    private void validateOwnership(Long professionalId, WorkSchedule existing) {
        if (!existing.getProfessionalId().equals(professionalId)) {
            log.warn("Tentativa de patch em horário que não pertence ao profissional. ProfessionalId: {}, ScheduleId: {}", professionalId, existing.getId());
            throw new WorkScheduleOwnershipException();
        }
    }

    private boolean isDayOfWeekBeingUpdated(WorkSchedule patch, WorkSchedule existing) {
        return patch.getDayOfWeek() != null && !patch.getDayOfWeek().equals(existing.getDayOfWeek());
    }

    private void validateConflict(Long professionalId, Long scheduleId, DayOfWeek newDayOfWeek) {
        boolean conflict = gateway.findByProfessionalId(professionalId).stream()
                .anyMatch(ws -> ws.getDayOfWeek().equals(newDayOfWeek) && !ws.getId().equals(scheduleId));

        if (conflict) {
            log.warn("Conflito de horário para profissional ID: {} no dia da semana: {}", professionalId, newDayOfWeek);
            throw new WorkScheduleConflictException(newDayOfWeek);
        }
    }

    private void updateTimes(WorkSchedule existing, WorkSchedule patch) {
        if (patch.getStartTime() != null) existing.setStartTime(patch.getStartTime());
        if (patch.getEndTime() != null) existing.setEndTime(patch.getEndTime());
        if (patch.getLunchStartTime() != null) existing.setLunchStartTime(patch.getLunchStartTime());
        if (patch.getLunchEndTime() != null) existing.setLunchEndTime(patch.getLunchEndTime());
    }
}


