package gestao.sghss.controllers.mappers;

import gestao.sghss.controllers.dtos.requests.CreateSpecialtyRequestDTO;
import gestao.sghss.controllers.dtos.responses.SpecialtyResponseDTO;
import gestao.sghss.domain.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Duration;

@Mapper(componentModel = "spring")
public interface SpecialtyControllerMapper {

    @Mapping(target = "consultationDuration", expression = "java(toDuration(request.consultationDurationMinutes()))")
    Specialty toDomain(CreateSpecialtyRequestDTO request);

    @Mapping(target = "consultationDurationMinutes", expression = "java(fromDuration(specialty.getConsultationDuration()))")
    SpecialtyResponseDTO toResponse(Specialty specialty);

    default Duration toDuration(Long minutes) {
        return minutes != null ? Duration.ofMinutes(minutes) : null;
    }

    default Long fromDuration(Duration duration) {
        return duration != null ? duration.toMinutes() : null;
    }
}

