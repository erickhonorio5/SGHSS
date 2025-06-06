package gestao.sghss.common.response;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public final class ErrorResponseBuilder {
    private ErrorResponseBuilder() {}

    public static ErrorResponse buildAuthenticationError(
            HttpServletRequest request,
            AuthenticationException ex,
            HttpStatus status) {
        return ErrorResponse.of(
                status.value(),
                status == HttpStatus.UNAUTHORIZED ? ErrorMessages.UNAUTHORIZED : ErrorMessages.FORBIDDEN,
                ex.getMessage(),
                request.getServletPath(),
                request.getRequestId()
        );
    }

    public static ErrorResponse buildGenericError(
            HttpServletRequest request,
            Exception ex,
            HttpStatus status) {
        return ErrorResponse.of(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getServletPath(),
                request.getRequestId()
        );
    }
}