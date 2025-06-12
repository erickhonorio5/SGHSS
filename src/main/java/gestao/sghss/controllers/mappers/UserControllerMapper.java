package gestao.sghss.controllers.mappers;

import gestao.sghss.annotations.EncodedMapping;
import gestao.sghss.controllers.dtos.requests.SignInRequestDTO;
import gestao.sghss.controllers.dtos.requests.SignUpRequestDTO;
import gestao.sghss.controllers.dtos.responses.UserResponseDTO;
import gestao.sghss.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        uses = PasswordEncoderMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserControllerMapper {

    @Mapping(source = "password", target = "passwordHash", qualifiedBy = EncodedMapping.class)
    @Mapping(target = "admin", expression = "java(false)")
    User toDomainRegister(SignUpRequestDTO dto);

    @Mapping(source = "password", target = "passwordHash")
    User toDomainLogin(SignInRequestDTO dto);

    UserResponseDTO toResponseDTO(User user);
}