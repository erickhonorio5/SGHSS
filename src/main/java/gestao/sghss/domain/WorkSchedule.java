package gestao.sghss.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkSchedule {
    private Long id;
    private Long professionalId;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public boolean containsTime(LocalDateTime dateTime) {
        if (!dateTime.getDayOfWeek().equals(this.dayOfWeek)) {
            return false;
        }

        LocalTime time = dateTime.toLocalTime();
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }
}
