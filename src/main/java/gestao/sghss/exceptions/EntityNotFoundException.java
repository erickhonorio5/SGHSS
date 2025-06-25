package gestao.sghss.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private static final String MSG = "Profissional não encontrado!";

    public EntityNotFoundException() {
        super(MSG);
    }
}
