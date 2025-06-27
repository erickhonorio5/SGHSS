package gestao.sghss.exceptions.handler;

import gestao.sghss.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NoPermissionException;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionFilters handleUserNotFound(final UserNotFoundException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(NOT_FOUND.value())
                .title("User not found!")
                .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionFilters handleGenericException(Exception ex, HttpServletRequest request) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(INTERNAL_SERVER_ERROR.value())
                .title("Internal Server Error")
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(WorkScheduleException.class)
    public ExceptionFilters handleWorkScheduleException(final WorkScheduleException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(BAD_REQUEST.value())
                .title("Erro na agenda de trabalho")
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(WorkScheduleNotFoundException.class)
    public ExceptionFilters handleWorkScheduleNotFound(WorkScheduleNotFoundException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(NOT_FOUND.value())
                .title("Horário de trabalho não encontrado")
                .build();
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(WorkScheduleOwnershipException.class)
    public ExceptionFilters handleWorkScheduleOwnership(WorkScheduleOwnershipException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(FORBIDDEN.value())
                .title("Horário não pertence ao profissional")
                .build();
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(WorkScheduleConflictException.class)
    public ExceptionFilters handleWorkScheduleConflict(WorkScheduleConflictException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(CONFLICT.value())
                .title("Conflito de horário")
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidWorkScheduleException.class)
    public ExceptionFilters handleInvalidSchedule(InvalidWorkScheduleException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(BAD_REQUEST.value())
                .title("Horário de trabalho inválido")
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NoChangesException.class)
    public ExceptionFilters handleNoChangesException(NoChangesException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(BAD_REQUEST.value())
                .title("Nenhuma modificação detectada")
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(WorkScheduleValidationException.class)
    public ExceptionFilters handleWorkScheduleValidationException(WorkScheduleValidationException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(BAD_REQUEST.value())
                .title("Erro de validação na agenda de trabalho")
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ExceptionFilters handleBusinessException(final BusinessException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(BAD_REQUEST.value())
                .title("Business rule violation")
                .build();
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(InvalidCredentialsException.class)
    public ExceptionFilters handleInvalidCretentials(final InvalidCredentialsException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(UNAUTHORIZED.value())
                .title("Invalid Credentials")
                .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ExceptionFilters nullPointer(final NullPointerException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(INTERNAL_SERVER_ERROR.value())
                .title("Nullpointer")
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NoPermissionException.class)
    public ExceptionFilters noPermissionException(NoPermissionException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(BAD_REQUEST.value())
                .title("NoPermissionException")
                .build();
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(PermissionDeniedException.class)
    public ExceptionFilters permissionDenied(final PermissionDeniedException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(UNAUTHORIZED.value())
                .title("Permission Denied!")
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionFilters illegalArgumentException(IllegalArgumentException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(BAD_REQUEST.value())
                .title("IllegalArgumentException")
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(PatientNotFoundException.class)
    public ExceptionFilters handlePatientNotFound(final PatientNotFoundException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(NOT_FOUND.value())
                .title("Patient not found!")
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionFilters handleProfessionalNotFound(final EntityNotFoundException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(NOT_FOUND.value())
                .title("Profissional não encontrado!")
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(SpecialtyException.class)
    public ExceptionFilters handleSpecialityNotFound(final SpecialtyException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(NOT_FOUND.value())
                .title("Especialidade não encontrada!")
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidPatientStatusException.class)
    public ExceptionFilters handleInvalidPatientStatus(final InvalidPatientStatusException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(BAD_REQUEST.value())
                .title("Invalid patient appointmentStatus")
                .build();
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(EntityConflictException.class)
    public ExceptionFilters handleEntityConflict(final EntityConflictException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(CONFLICT.value())
                .title("Entity conflict")
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(AppointmentNotFoundException.class)
    public ExceptionFilters handleAppointmentNotFound(AppointmentNotFoundException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(NOT_FOUND.value())
                .title("Consulta não encontrada!")
                .build();
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(AppointmentConflictException.class)
    public ExceptionFilters handleAppointmentConflict(AppointmentConflictException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(CONFLICT.value())
                .title("Conflito de agendamento!")
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(AppointmentValidationException.class)
    public ExceptionFilters handleAppointmentValidation(AppointmentValidationException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(BAD_REQUEST.value())
                .title("Erro de validação na consulta!")
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidAppointmentException.class)
    public ExceptionFilters handleInvalidAppointmentException(InvalidAppointmentException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(BAD_REQUEST.value())
                .title("Invalid Appointment")
                .build();
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(AppointmentSlotUnavailableException.class)
    public ExceptionFilters handleAppointmentSlotUnavailableException(AppointmentSlotUnavailableException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(CONFLICT.value())
                .title("Horário de consulta indisponível")
                .build();
    }
}