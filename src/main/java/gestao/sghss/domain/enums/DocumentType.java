package gestao.sghss.domain.enums;

import lombok.Getter;

@Getter
public enum DocumentType {
    CRM("Regional Medical Council"),
    CRO("Regional Dentistry Council"),
    CRN("Regional Nutrition Council"),
    CREFITO("Regional Physiotherapy Council"),
    CRP("Regional Psychology Council"),
    COREN("Regional Nursing Council");

    private final String description;

    DocumentType(String description) {
        this.description = description;
    }
}
