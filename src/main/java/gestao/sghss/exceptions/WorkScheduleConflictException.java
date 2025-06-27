package gestao.sghss.exceptions;

import java.time.DayOfWeek;

public class WorkScheduleConflictException extends WorkScheduleException {
    public WorkScheduleConflictException(DayOfWeek day) {
        super("Já existe horário definido para o dia: " + day);
    }
}
