package gestao.sghss.domain.enums;

import lombok.Getter;

@Getter
public enum AppointmentStatus {
    SCHEDULED("Agendado"),
    CONFIRMED("Confirmado"),
    COMPLETED("Concluído"),
    CANCELLED("Cancelado");

    private final String description;

    AppointmentStatus(String description) {
        this.description = description;
    }
}