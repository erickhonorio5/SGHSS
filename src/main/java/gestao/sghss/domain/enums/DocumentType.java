package gestao.sghss.domain.enums;

import lombok.Getter;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Schema(description = "Tipo de documento do profissional")
public enum DocumentType {

    @Schema(description = "CRM - Conselho Regional de Medicina")
    CRM("Regional Medical Council"),

    @Schema(description = "CRO - Conselho Regional de Odontologia")
    CRO("Regional Dentistry Council"),

    @Schema(description = "CRN - Conselho Regional de Nutrição")
    CRN("Regional Nutrition Council"),

    @Schema(description = "CREFITO - Conselho Regional de Fisioterapia e Terapia Ocupacional")
    CREFITO("Regional Physiotherapy Council"),

    @Schema(description = "CRP - Conselho Regional de Psicologia")
    CRP("Regional Psychology Council"),

    @Schema(description = "COREN - Conselho Regional de Enfermagem")
    COREN("Regional Nursing Council");

    private final String description;

    DocumentType(String description) {
        this.description = description;
    }
}

