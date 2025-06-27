package gestao.sghss.exceptions;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(Long appointmentId) {
        super("Agendamento não encontrado para ID: " + appointmentId);
    }
}