package gestao.sghss.controllers.mappers;

import gestao.sghss.controllers.dtos.requests.CreateWorkScheduleRequestDTO;
import gestao.sghss.controllers.dtos.responses.WorkScheduleResponseDTO;
import gestao.sghss.domain.WorkSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkScheduleControllerMapper {

    WorkSchedule toDomain(CreateWorkScheduleRequestDTO dto);

    WorkScheduleResponseDTO toResponse(WorkSchedule workSchedule);
}

