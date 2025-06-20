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

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidPatientStatusException.class)
    public ExceptionFilters handleInvalidPatientStatus(final InvalidPatientStatusException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(BAD_REQUEST.value())
                .title("Invalid patient status")
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
}
