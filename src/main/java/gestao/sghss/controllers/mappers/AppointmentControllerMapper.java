package gestao.sghss.controllers.mappers;

import gestao.sghss.controllers.dtos.requests.AppointmentCreateRequestDTO;
import gestao.sghss.controllers.dtos.responses.AppointmentResponseDTO;
import gestao.sghss.domain.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentControllerMapper {

    Appointment toDomain(AppointmentCreateRequestDTO request);

    AppointmentResponseDTO toResponse(Appointment appointment);
}
