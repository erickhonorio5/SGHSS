package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.gateways.entities.WorkScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkScheduleMapper {

    @Mapping(target = "professional.id", source = "professionalId") // 💡 direto!
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    WorkScheduleEntity toEntity(WorkSchedule domain);

    @Mapping(target = "professionalId", source = "professional.id")
    WorkSchedule toDomain(WorkScheduleEntity entity);
}
