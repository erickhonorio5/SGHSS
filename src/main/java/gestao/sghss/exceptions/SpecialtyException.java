package gestao.sghss.exceptions;

public class SpecialtyException extends RuntimeException {

    private static final String MSG = "Especialidade não encontrada!";

    public SpecialtyException() {
        super(MSG);
    }
}
