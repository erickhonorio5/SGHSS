package gestao.sghss.controllers.dtos.responses;

import gestao.sghss.domain.enums.DocumentType;

import java.time.LocalDateTime;
import java.util.List;

public record ProfessionalResponseDTO(
        Long id,
        String name,
        String document,
        DocumentType documentType,
        List<SpecialtyResponseDTO> specialties,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
