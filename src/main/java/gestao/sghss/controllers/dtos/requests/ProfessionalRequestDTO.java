package gestao.sghss.controllers.dtos.requests;

import gestao.sghss.domain.enums.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfessionalRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String document;

    @NotNull
    private DocumentType documentType;

    @NotNull
    private List<Long> specialtiesIds;

    private List<WorkScheduleRequestDTO> workSchedules;
}
