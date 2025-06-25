package gestao.sghss.controllers.dtos.responses;

import gestao.sghss.domain.enums.DocumentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProfessionalResponseDTO {

    private Long id;
    private String name;
    private String document;
    private DocumentType documentType;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<SpecialtyResponseDTO> specialties;
    private List<WorkScheduleResponseDTO> workSchedules;
}
