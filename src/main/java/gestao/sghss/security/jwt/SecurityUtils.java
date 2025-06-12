package gestao.sghss.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import gestao.sghss.exceptions.handler.ExceptionFilters;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private static final List<String> SECURITY_HEADERS = Arrays.asList(
            "X-Content-Type-Options",
            "X-Frame-Options",
            "X-XSS-Protection",
            "Strict-Transport-Security",
            "Content-Security-Policy"
    );

    private final ObjectMapper objectMapper;
    
    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    public void addSecurityHeaders(HttpServletResponse response) {
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-Frame-Options", "DENY");
        response.setHeader("X-XSS-Protection", "1; mode=block");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        if (isProd()) {
            response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
            response.setHeader("Content-Security-Policy", "default-src 'self'");
        }
    }

    public void writeErrorResponse(
            HttpServletRequest request,
            HttpServletResponse response,
            Exception ex,
            int status) throws IOException {

        HttpStatus httpStatus = HttpStatus.valueOf(status);

        ExceptionFilters error = ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(httpStatus.value())
                .title(ex instanceof AuthenticationException ? "Unauthorized" : httpStatus.getReasonPhrase())
                .build();

        writeResponse(response, error, status);
    }

    private void writeResponse(
            HttpServletResponse response,
            ExceptionFilters errorResponse,
            int status) throws IOException {
        addSecurityHeaders(response);
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }

    public boolean hasSecurityHeaders(HttpServletResponse response) {
        return SECURITY_HEADERS.stream()
                .allMatch(header -> response.getHeader(header) != null);
    }

    private boolean isProd() {
        return "prod".equals(activeProfile);
    }

    public boolean isSecureRequest(HttpServletRequest request) {
        return request.isSecure() || "https".equalsIgnoreCase(request.getHeader("X-Forwarded-Proto"));
    }
}