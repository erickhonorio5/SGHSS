package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.User;
import gestao.sghss.gateways.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserGatewayMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}