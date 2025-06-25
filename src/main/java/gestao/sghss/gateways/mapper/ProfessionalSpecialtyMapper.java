package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.Specialty;
import gestao.sghss.gateways.entities.ProfessionalSpecialtyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { SpecialtyMapper.class })
public interface ProfessionalSpecialtyMapper {

    @Mapping(source = "specialty.id", target = "id")
    @Mapping(source = "specialty.name", target = "name")
    @Mapping(source = "specialty.description", target = "description")
    @Mapping(source = "specialty.consultationDurationMinutes", target = "consultationDuration")
    Specialty toDomain(ProfessionalSpecialtyEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professional", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProfessionalSpecialtyEntity toEntity(Specialty specialty);
}





