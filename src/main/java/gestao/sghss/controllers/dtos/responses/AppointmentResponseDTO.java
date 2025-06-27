package gestao.sghss.controllers.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import gestao.sghss.domain.enums.AppointmentStatus;
import gestao.sghss.domain.enums.AppointmentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class AppointmentResponseDTO {

    private Long appointmentId;

    private Long patientId;

    private Long professionalId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime appointmentTime;

    private AppointmentType appointmentType;

    private AppointmentStatus appointmentStatus;

    private String observations;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

