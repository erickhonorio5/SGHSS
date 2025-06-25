package gestao.sghss.controllers.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record WorkScheduleResponseDTO(

        @Schema(description = "ID do horário de trabalho")
        Long id,

        @Schema(description = "Dia da semana")
        DayOfWeek dayOfWeek,

        @Schema(description = "Hora de início")
        LocalTime startTime,

        @Schema(description = "Hora de fim")
        LocalTime endTime
) {}
