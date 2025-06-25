package gestao.sghss.controllers.mappers;

import gestao.sghss.controllers.dtos.requests.CreateSpecialtyRequestDTO;
import gestao.sghss.controllers.dtos.responses.SpecialtyResponseDTO;
import gestao.sghss.domain.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpecialtyControllerMapper {

    @Mapping(target = "consultationDuration", source = "consultationDurationMinutes")
    Specialty toDomain(CreateSpecialtyRequestDTO request);

    @Mapping(target = "consultationDurationMinutes", source = "consultationDuration")
    SpecialtyResponseDTO toResponse(Specialty specialty);
}

