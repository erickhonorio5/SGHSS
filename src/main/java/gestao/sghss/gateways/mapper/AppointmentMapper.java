package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.Appointment;
import gestao.sghss.gateways.entities.AppointmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentEntity toEntity(Appointment domain);

    Appointment toDomain(AppointmentEntity entity);
}