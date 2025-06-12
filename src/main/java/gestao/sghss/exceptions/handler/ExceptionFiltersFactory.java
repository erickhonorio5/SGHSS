package gestao.sghss.exceptions.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public final class ExceptionFiltersFactory {
    private ExceptionFiltersFactory() {}

    public static ExceptionFilters of(Exception ex, HttpServletRequest request, HttpStatus status, String title) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(status.value())
                .title(title)
                .build();
    }
}

