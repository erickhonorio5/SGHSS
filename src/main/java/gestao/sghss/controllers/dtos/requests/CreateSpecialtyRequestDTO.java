package gestao.sghss.controllers.dtos.requests;

public record CreateSpecialtyRequestDTO (
        String name,
        String description,
        Long consultationDurationMinutes
) {}
