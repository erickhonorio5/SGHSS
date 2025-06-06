package gestao.sghss.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDTO {
    private String username;
    private String email;
    private String fullName;
    private boolean isAccountVerified;
    private LocalDateTime lastAccess;
    private LocalDateTime createdAt;
}