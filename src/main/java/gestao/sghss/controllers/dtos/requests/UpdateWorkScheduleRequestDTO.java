package gestao.sghss.controllers.dtos.requests;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record UpdateWorkScheduleRequestDTO(
        @NotNull LocalTime startTime,
        @NotNull LocalTime endTime,
        LocalTime lunchStartTime,
        LocalTime lunchEndTime
) {}

