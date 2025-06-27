package gestao.sghss.controllers.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record UpdateWorkScheduleRequestDTO(

        @Schema(description = "Dia da semana", example = "MONDAY")
        @NotNull
        DayOfWeek dayOfWeek,

        @Schema(description = "Hora de início", example = "08:00")
        @NotNull
        LocalTime startTime,

        @Schema(description = "Hora de fim", example = "17:00")
        @NotNull
        LocalTime endTime,

        @Schema(description = "Início do intervalo de almoço", example = "12:00")
        LocalTime lunchStartTime,

        @Schema(description = "Fim do intervalo de almoço", example = "13:00")
        LocalTime lunchEndTime
) {}