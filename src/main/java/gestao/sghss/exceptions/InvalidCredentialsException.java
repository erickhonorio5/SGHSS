package gestao.sghss.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    private static final String MSG = "invalid username/email or password";

    public InvalidCredentialsException() {
        super(MSG);
    }
}
