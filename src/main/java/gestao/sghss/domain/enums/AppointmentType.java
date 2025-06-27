package gestao.sghss.domain.enums;

import lombok.Getter;

@Getter
public enum AppointmentType {
    IN_PERSON("Consulta presencial"),
    TELEMEDICINE("Consulta de Telemedicina");

    private final String description;

    AppointmentType(String description) {
        this.description = description;
    }
}