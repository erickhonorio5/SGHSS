package gestao.sghss.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specialty {
    private Long id;
    private String name;
    private String description;
    private Duration consultationDuration;

    public long getConsultationDurationMinutes() {
        return consultationDuration != null ? consultationDuration.toMinutes() : 0;
    }

    public void setConsultationDurationMinutes(long minutes) {
        this.consultationDuration = Duration.ofMinutes(minutes);
    }
}