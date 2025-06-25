package gestao.sghss.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSpecialtyRequestDTO {

    private String name;
    private String description;
    private Long consultationDurationMinutes;
}
