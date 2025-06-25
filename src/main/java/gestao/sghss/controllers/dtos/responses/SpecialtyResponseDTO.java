package gestao.sghss.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialtyResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Long consultationDurationMinutes;
}
