package gestao.sghss.controllers.dtos.requests;

import gestao.sghss.domain.enums.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateProfessionalRequestDTO(

        @Schema(description = "Nome completo do profissional")
        @NotBlank
        String name,

        @Schema(description = "Documento profissional (ex: CRM123456)")
        @NotBlank
        String document,

        @Schema(
                description = "Tipo de documento do profissional",
                allowableValues = {"CRM", "CRO", "CRN", "CREFITO", "CRP", "COREN"}
        )
        @NotNull
        DocumentType documentType,

        @Schema(description = "Lista de IDs de especialidades atribuídas ao profissional")
        @NotEmpty
        List<Long> specialtyIds
) {}
