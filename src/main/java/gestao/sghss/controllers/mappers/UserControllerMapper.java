package gestao.sghss.controllers.mappers;

import gestao.sghss.controllers.dtos.requests.SignInRequestDTO;
import gestao.sghss.controllers.dtos.requests.SignUpRequestDTO;
import gestao.sghss.controllers.dtos.responses.UserResponseDTO;
import gestao.sghss.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserControllerMapper {

    private final PasswordEncoderMapper passwordEncoderMapper;

    @Autowired
    public UserControllerMapper(PasswordEncoderMapper passwordEncoderMapper) {
        this.passwordEncoderMapper = passwordEncoderMapper;
    }

    public User toDomainRegister(SignUpRequestDTO dto) {
        LocalDateTime now = LocalDateTime.now();

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setPasswordHash(passwordEncoderMapper.encode(dto.getPassword()));
        user.setAccountVerified(false);
        user.setResetOtpExpiryAt(0L);
        user.setVerifyOtp(null);
        user.setVerifyOtpExpiryAt(0L);
        user.setResetOtp(null);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setLastAccess(now);

        return user;
    }

    public User toDomainLogin(SignInRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPasswordHash(dto.getPassword());
        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setAccountVerified(user.isAccountVerified());
        dto.setLastAccess(user.getLastAccess());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}