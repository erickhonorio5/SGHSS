package gestao.sghss.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ErrorResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime timestamp;
    
    int status;
    String error;
    String message;
    String path;
    String requestId;

    public static ErrorResponse of(
            int status,
            String error,
            String message,
            String path,
            String requestId) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .error(error)
                .message(message)
                .path(path)
                .requestId(requestId)
                .build();
    }
}