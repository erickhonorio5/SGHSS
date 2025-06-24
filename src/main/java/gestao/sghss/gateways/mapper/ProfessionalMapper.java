package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.Professional;
import gestao.sghss.gateways.entities.ProfessionalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = { WorkScheduleMapper.class, ProfessionalSpecialtyMapper.class }
)
public interface ProfessionalMapper {

    @Mapping(target = "specialties", source = "specialties")
    @Mapping(target = "workSchedules", source = "workSchedules")
    Professional toDomain(ProfessionalEntity entity);

    @Mapping(target = "specialties", source = "specialties")
    @Mapping(target = "workSchedules", source = "workSchedules")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProfessionalEntity toEntity(Professional domain);
}

