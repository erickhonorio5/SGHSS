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
@Component
@RequiredArgsConstructor
public class UpdateWorkScheduleUseCase {

    private final WorkScheduleGateway workScheduleGateway;
    private final ProfessionalGateway professionalGateway;

    public WorkSchedule execute(Long professionalId, Long scheduleId, WorkSchedule updatedSchedule) {
        log.info("Atualizando horário de trabalho ID: {} para profissional ID: {}", scheduleId, professionalId);

        validateProfessionalExists(professionalId);

        WorkSchedule existingSchedule = findExistingSchedule(scheduleId);

        validateOwnership(professionalId, existingSchedule);

        validateDayOfWeekConflict(professionalId, scheduleId, updatedSchedule.getDayOfWeek());

        existingSchedule.setDayOfWeek(updatedSchedule.getDayOfWeek());
        existingSchedule.setStartTime(updatedSchedule.getStartTime());
        existingSchedule.setEndTime(updatedSchedule.getEndTime());
        existingSchedule.setLunchStartTime(updatedSchedule.getLunchStartTime());
        existingSchedule.setLunchEndTime(updatedSchedule.getLunchEndTime());

        WorkSchedule saved = workScheduleGateway.save(existingSchedule);
        log.info("Horário atualizado com sucesso: {}", saved);
        return saved;
    }

    private void validateProfessionalExists(Long professionalId) {
        professionalGateway.findById(professionalId);
    }

    private WorkSchedule findExistingSchedule(Long scheduleId) {
        return workScheduleGateway.findById(scheduleId);
    }

    private void validateOwnership(Long professionalId, WorkSchedule existingSchedule) {
        if (!existingSchedule.getProfessionalId().equals(professionalId)) {
            log.warn("Tentativa de atualizar horário que não pertence ao profissional. ProfessionalId: {}, ScheduleId: {}", professionalId, existingSchedule.getId());
            throw new WorkScheduleOwnershipException();
        }
    }

    private void validateDayOfWeekConflict(Long professionalId, Long scheduleId, DayOfWeek newDayOfWeek) {
        boolean hasConflict = workScheduleGateway.findByProfessionalId(professionalId).stream()
                .anyMatch(ws -> ws.getDayOfWeek().equals(newDayOfWeek) && !ws.getId().equals(scheduleId));
        if (hasConflict) {
            log.warn("Conflito de horário para profissional ID: {} no dia da semana: {}", professionalId, newDayOfWeek);
            throw new WorkScheduleConflictException(newDayOfWeek);
        }
    }
}


