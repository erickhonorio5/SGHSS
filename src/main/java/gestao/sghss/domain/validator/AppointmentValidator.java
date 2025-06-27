package gestao.sghss.domain.validator;

import gestao.sghss.exceptions.InvalidAppointmentException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class AppointmentValidator {

    public void validateAppointmentDateTime(LocalDate appointmentDate, LocalTime appointmentTime) {
        if (appointmentDate == null) {
            throw new InvalidAppointmentException("Data da consulta não pode ser nula");
        }
        if (appointmentTime == null) {
            throw new InvalidAppointmentException("Horário da consulta não pode ser nulo");
        }

        LocalDateTime combinedDateTime = LocalDateTime.of(appointmentDate, appointmentTime);

        if (combinedDateTime.isBefore(LocalDateTime.now())) {
            throw new InvalidAppointmentException("Data e horário da consulta devem ser no futuro");
        }
    }

    public void validateObservations(String observations) {
        if (observations != null && observations.length() > 500) {
            throw new InvalidAppointmentException("Observações não podem exceder 500 caracteres");
        }
    }
}



