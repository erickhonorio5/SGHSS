package gestao.sghss.controllers.dtos.responses;

public record SpecialtyResponseDTO(
        Long id,
        String name,
        String description,
        Long consultationDurationMinutes
) {}
