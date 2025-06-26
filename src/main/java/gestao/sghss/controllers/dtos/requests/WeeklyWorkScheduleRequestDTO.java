package gestao.sghss.controllers.dtos.requests;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record WeeklyWorkScheduleRequestDTO(
        @NotNull List<CreateWorkScheduleRequestDTO> schedules
) {}

