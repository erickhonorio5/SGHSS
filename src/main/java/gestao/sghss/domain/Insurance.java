package gestao.sghss.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {
    private String name;
    private String number;
    private LocalDate expiryDate;

    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }

    public boolean isValid() {
        return !isExpired();
    }
}
