package gestao.sghss.controllers.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRescheduleRequestDTO(
        @NotNull(message = "Data da nova consulta é obrigatória")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate newAppointmentDate,

        @NotNull(message = "Horário da nova consulta é obrigatório")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime newAppointmentTime
) {}

