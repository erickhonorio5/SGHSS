package gestao.sghss.domain.validator;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.exceptions.InvalidWorkScheduleException;
import gestao.sghss.exceptions.WorkScheduleConflictException;
import gestao.sghss.exceptions.WorkScheduleOwnershipException;
import org.springframework.stereotype.Component;

@Component
public class WorkScheduleValidator {

    public void validateTimes(LocalTime start, LocalTime end, LocalTime lunchStart, LocalTime lunchEnd) {
        if (start != null && end != null && start.isAfter(end)) {
            throw new InvalidWorkScheduleException("Hora inicial não pode ser depois da final.");
        }

        if (lunchStart != null && lunchEnd != null) {
            if (lunchStart.isAfter(lunchEnd)) {
                throw new InvalidWorkScheduleException("Almoço inválido: início depois do fim.");
            }
            if (start != null && lunchStart.isBefore(start)) {
                throw new InvalidWorkScheduleException("Almoço começa antes do expediente.");
            }
            if (end != null && lunchEnd.isAfter(end)) {
                throw new InvalidWorkScheduleException("Almoço termina após o expediente.");
            }
        }
    }

    public void validateNoDuplicateDay(DayOfWeek dayOfWeek, Long professionalId, Long currentScheduleId, List<WorkSchedule> schedules) {
        boolean exists = schedules.stream()
                .anyMatch(ws -> ws.getDayOfWeek().equals(dayOfWeek)
                        && !ws.getId().equals(currentScheduleId));

        if (exists) {
            throw new WorkScheduleConflictException(dayOfWeek);
        }
    }

    public void validateOwnership(Long professionalId, WorkSchedule schedule) {
        if (!professionalId.equals(schedule.getProfessionalId())) {
            throw new WorkScheduleOwnershipException();
        }
    }
}