package gestao.sghss.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
@Setter
@EqualsAndHashCode
public class User implements Serializable {

    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String passwordHash;
    private String verifyOtp;
    private boolean isAccountVerified;
    private Boolean admin;
    private Long verifyOtpExpiryAt;
    private String resetOtp;
    private Long resetOtpExpiryAt;
    private LocalDateTime createdAt;
    private LocalDateTime lastAccess;
    private LocalDateTime updatedAt;
    private String lastLogin;

    public User() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.lastAccess = now;
        this.isAccountVerified = false;
    }

    public void setLastAccess() {
        if (lastAccess == null) {
            this.lastAccess = now();
        }
    }

    public String getLastLogin() {
        return (lastLogin == null) ? this.email : this.lastLogin;
    }
}
