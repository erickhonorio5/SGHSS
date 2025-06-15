package gestao.sghss.controllers.mappers;

import gestao.sghss.controllers.dtos.InsuranceDTO;
import gestao.sghss.domain.Insurance;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InsuranceControllerMapper {

    Insurance toDomain(InsuranceDTO dto);

    InsuranceDTO toDto(Insurance domain);

    List<Insurance> toDomainList(List<InsuranceDTO> dtos);

    List<InsuranceDTO> toDtoList(List<Insurance> domains);
}
