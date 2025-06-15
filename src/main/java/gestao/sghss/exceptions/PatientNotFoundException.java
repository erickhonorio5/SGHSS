package gestao.sghss.exceptions;

public class PatientNotFoundException extends RuntimeException {

    private static final String MSG = "Paciente não encontrado!";

    public PatientNotFoundException() {
        super(MSG);
    }
}
