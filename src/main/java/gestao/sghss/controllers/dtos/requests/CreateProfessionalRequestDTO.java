package gestao.sghss.controllers.dtos.requests;

import gestao.sghss.domain.enums.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record CreateProfessionalRequestDTO(

        @Schema(description = "Nome completo do profissional")
        String name,

        @Schema(description = "Documento profissional (ex: CRM123456)")
        String document,

        @Schema(
                description = "Tipo de documento do profissional",
                allowableValues = {"CRM", "CRO", "CRN", "CREFITO", "CRP", "COREN"}
        )
        DocumentType documentType,

        @Schema(description = "Lista de IDs de especialidades atribuídas ao profissional")
        List<Long> specialtyIds
) {}

