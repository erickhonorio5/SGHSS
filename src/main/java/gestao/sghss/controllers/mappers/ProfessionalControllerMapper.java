package gestao.sghss.controllers.mappers;

import gestao.sghss.controllers.dtos.requests.CreateProfessionalRequestDTO;
import gestao.sghss.controllers.dtos.requests.UpdateProfessionalRequestDTO;
import gestao.sghss.controllers.dtos.responses.ProfessionalResponseDTO;
import gestao.sghss.domain.Professional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { SpecialtyControllerMapper.class })
public interface ProfessionalControllerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "specialties", ignore = true)
    @Mapping(target = "workSchedules", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Professional toDomain(CreateProfessionalRequestDTO request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "specialties", ignore = true)
    @Mapping(target = "workSchedules", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Professional toDomain(UpdateProfessionalRequestDTO request);

    ProfessionalResponseDTO toResponse(Professional professional);
}

