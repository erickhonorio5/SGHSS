package gestao.sghss.controllers.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record CreateWorkScheduleRequestDTO(

        @NotNull
        @Schema(description = "Dia da semana (ex: MONDAY)", example = "MONDAY")
        DayOfWeek dayOfWeek,

        @NotNull
        @Schema(description = "Hora de início (formato HH:mm)", example = "08:00")
        LocalTime startTime,

        @NotNull
        @Schema(description = "Hora de fim (formato HH:mm)", example = "17:00")
        LocalTime endTime,

        @Schema(description = "Início do almoço (formato HH:mm)", example = "12:00")
        LocalTime lunchStartTime,

        @Schema(description = "Fim do almoço (formato HH:mm)", example = "13:00")
        LocalTime lunchEndTime

) {}
