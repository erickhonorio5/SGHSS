package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.Specialty;
import gestao.sghss.gateways.entities.ProfessionalSpecialtyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { SpecialtyMapper.class })
public interface ProfessionalSpecialtyMapper {

    SpecialtyMapper specialtyMapper = null; // será injetado automaticamente

    default Specialty toDomain(ProfessionalSpecialtyEntity entity) {
        if (entity == null || entity.getSpecialty() == null) {
            return null;
        }
        return specialtyMapper.toDomain(entity.getSpecialty());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professional", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProfessionalSpecialtyEntity toEntity(Specialty specialty);
}





