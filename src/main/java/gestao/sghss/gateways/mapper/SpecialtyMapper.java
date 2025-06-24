package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.Specialty;
import gestao.sghss.gateways.entities.SpecialtyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Duration;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    @Mapping(source = "consultationDurationMinutes", target = "consultationDuration")
    Specialty toDomain(SpecialtyEntity entity);

    @Mapping(source = "consultationDuration", target = "consultationDurationMinutes")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SpecialtyEntity toEntity(Specialty domain);

    default Duration mapToDuration(Long minutes) {
        return minutes != null ? Duration.ofMinutes(minutes) : null;
    }

    default Long mapToMinutes(Duration duration) {
        return duration != null ? duration.toMinutes() : null;
    }
}





