package gestao.sghss.gateways.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`users`")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @Size(max = 50)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @Size(max = 100)
    private String passwordHash;

    @Column
    private String verifyOtp;

    @Column(nullable = false)
    private boolean isAccountVerified;

    @Column(nullable = false)
    private Boolean admin = false;

    @Column
    private Long verifyOtpExpiryAt;

    @Column
    private String resetOtp;

    @Column
    private Long resetOtpExpiryAt;

    @Column
    private String lastLogin;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastAccess;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}