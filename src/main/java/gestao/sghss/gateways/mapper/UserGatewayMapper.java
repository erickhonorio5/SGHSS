package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.User;
import gestao.sghss.gateways.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserGatewayMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        User domain = new User();
        domain.setId(entity.getId());
        domain.setUsername(entity.getUsername());
        domain.setEmail(entity.getEmail());
        domain.setFullName(entity.getFullName());
        domain.setPasswordHash(entity.getPasswordHash());
        domain.setVerifyOtp(entity.getVerifyOtp());
        domain.setAccountVerified(entity.isAccountVerified());
        domain.setVerifyOtpExpiryAt(entity.getVerifyOtpExpiryAt());
        domain.setResetOtp(entity.getResetOtp());
        domain.setResetOtpExpiryAt(entity.getResetOtpExpiryAt());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setLastAccess(entity.getLastAccess());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setLastLogin(entity.getLastLogin());

        return domain;
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setEmail(domain.getEmail());
        entity.setFullName(domain.getFullName());
        entity.setPasswordHash(domain.getPasswordHash());
        entity.setVerifyOtp(domain.getVerifyOtp());
        entity.setAccountVerified(domain.isAccountVerified());
        entity.setVerifyOtpExpiryAt(domain.getVerifyOtpExpiryAt());
        entity.setResetOtp(domain.getResetOtp());
        entity.setResetOtpExpiryAt(domain.getResetOtpExpiryAt());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setLastAccess(domain.getLastAccess());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setLastLogin(domain.getLastLogin());

        return entity;
    }
}