package gestao.sghss.controllers.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record WorkScheduleResponseDTO(

        @Schema(description = "ID do horário de trabalho", example = "1")
        Long id,

        @Schema(description = "Dia da semana", example = "MONDAY")
        DayOfWeek dayOfWeek,

        @Schema(description = "Hora de início", example = "08:00")
        LocalTime startTime,

        @Schema(description = "Hora de fim", example = "17:00")
        LocalTime endTime,

        @Schema(description = "Início do almoço", example = "12:00")
        LocalTime lunchStartTime,

        @Schema(description = "Fim do almoço", example = "13:00")
        LocalTime lunchEndTime

) {}
