package gestao.sghss.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private Address address;
    private Insurance insurance;

    @Builder.Default
    private boolean active = true;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public int getAge() {
        return LocalDate.now().getYear() - this.birthDate.getYear();
    }
}
