package gestao.sghss.exceptions;

public class AppointmentSlotUnavailableException extends RuntimeException {
  public AppointmentSlotUnavailableException(String message) {
    super(message);
  }

  public AppointmentSlotUnavailableException() {
    super("O horário solicitado para a consulta não está disponível.");
  }
}
