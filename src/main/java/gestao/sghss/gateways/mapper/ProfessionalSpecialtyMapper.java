package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.Specialty;
import gestao.sghss.gateways.entities.ProfessionalSpecialtyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { SpecialtyMapper.class })
public interface ProfessionalSpecialtyMapper {

    @Mapping(source = "specialty", target = ".")
    Specialty toDomain(ProfessionalSpecialtyEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professional", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProfessionalSpecialtyEntity toEntity(Specialty specialty);
}





