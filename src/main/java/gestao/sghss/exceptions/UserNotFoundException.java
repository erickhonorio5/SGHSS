package gestao.sghss.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final String MSG = "Usuário não encontrado!";

    public UserNotFoundException() {
        super(MSG);
    }
}