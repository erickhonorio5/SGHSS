package gestao.sghss.domain;

import gestao.sghss.domain.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professional {

    private Long id;
    private Long userId;
    private String name;
    private String document;
    private DocumentType documentType;
    private List<Specialty> specialties;
    private List<WorkSchedule> workSchedules;

    @Builder.Default
    private boolean active = true;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void activate() {
        this.active = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean hasSpecialty(Long specialtyId) {
        return specialties != null && specialties.stream()
                .anyMatch(specialty -> specialty.getId().equals(specialtyId));
    }

    public boolean canAttendAt(LocalDateTime dateTime) {
        return active && workSchedules != null && workSchedules.stream()
                .anyMatch(schedule -> schedule.containsTime(dateTime));
    }
}

