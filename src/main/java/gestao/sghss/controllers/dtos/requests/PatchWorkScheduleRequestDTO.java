package gestao.sghss.controllers.dtos.requests;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record PatchWorkScheduleRequestDTO(
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        LocalTime lunchStartTime,
        LocalTime lunchEndTime
) {}
