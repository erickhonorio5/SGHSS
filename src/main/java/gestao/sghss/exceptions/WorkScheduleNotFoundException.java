package gestao.sghss.exceptions;

public class WorkScheduleNotFoundException extends WorkScheduleException {
    public WorkScheduleNotFoundException(Long id) {
        super("Horário de trabalho não encontrado para ID: " + id);
    }
}
