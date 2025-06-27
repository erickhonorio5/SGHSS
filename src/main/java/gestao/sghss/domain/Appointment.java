package gestao.sghss.domain;

import gestao.sghss.domain.enums.AppointmentStatus;
import gestao.sghss.domain.enums.AppointmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    private Long appointmentId;
    private Long patientId;
    private Long professionalId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private AppointmentType appointmentType;
    private AppointmentStatus appointmentStatus;
    private String observations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public boolean canConfirm() {return AppointmentStatus.SCHEDULED.equals(this.appointmentStatus);}

    public void confirm() { if (canConfirm()) this.appointmentStatus = AppointmentStatus.CONFIRMED;}

    public boolean canComplete() {return AppointmentStatus.CONFIRMED.equals(this.appointmentStatus) || AppointmentStatus.SCHEDULED.equals(this.appointmentStatus);}

    public void complete() {if (canComplete()) this.appointmentStatus = AppointmentStatus.COMPLETED;}

    public boolean canCancel() {return !AppointmentStatus.COMPLETED.equals(this.appointmentStatus);}

    public void cancel() {if (canCancel()) this.appointmentStatus = AppointmentStatus.CANCELLED;}

    public boolean canReschedule() {return !(AppointmentStatus.COMPLETED.equals(this.appointmentStatus) || AppointmentStatus.CANCELLED.equals(this.appointmentStatus));}

    public void reschedule(LocalDate newDateTime, LocalTime newTime) {
        if (canReschedule()) {
            this.appointmentDate = newDateTime;
            this.appointmentTime = newTime;
            this.appointmentStatus = AppointmentStatus.SCHEDULED;
        }
    }
}