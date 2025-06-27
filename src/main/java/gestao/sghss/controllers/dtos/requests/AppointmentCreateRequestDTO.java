package gestao.sghss.controllers.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import gestao.sghss.domain.enums.AppointmentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AppointmentCreateRequestDTO {

    @NotNull(message = "O ID do paciente é obrigatório")
    private Long patientId;

    @NotNull(message = "O ID do profissional é obrigatório")
    private Long professionalId;

    @NotNull(message = "Data da consulta é obrigatório")
    @Schema(example = "2025-07-01", description = "Data da consulta no formato yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    @NotNull(message = "Horário da consulta é obrigatório")
    @Schema(example = "14:30", description = "Hora da consulta no formato HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime appointmentTime;

    private AppointmentType appointmentType;

    private String observations;
}
