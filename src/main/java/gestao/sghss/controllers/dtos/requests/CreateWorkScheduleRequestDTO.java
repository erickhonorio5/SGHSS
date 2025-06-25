package gestao.sghss.controllers.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record CreateWorkScheduleRequestDTO(

        @Schema(description = "Dia da semana (ex: MONDAY)", example = "MONDAY")
        DayOfWeek dayOfWeek,

        @Schema(description = "Hora de início (formato HH:mm)", example = "08:00")
        LocalTime startTime,

        @Schema(description = "Hora de fim (formato HH:mm)", example = "17:00")
        LocalTime endTime
) {}
