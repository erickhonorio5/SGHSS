package gestao.sghss.exceptions;

public class WorkScheduleOwnershipException extends WorkScheduleException {
    public WorkScheduleOwnershipException() {
        super("Esse horário de trabalho não pertence ao profissional informado.");
    }
}
