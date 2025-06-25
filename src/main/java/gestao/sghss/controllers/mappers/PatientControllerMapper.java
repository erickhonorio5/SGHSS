package gestao.sghss.controllers.mappers;

import gestao.sghss.controllers.dtos.requests.PatientPatchRequestDTO;
import gestao.sghss.controllers.dtos.requests.PatientRequestDTO;
import gestao.sghss.controllers.dtos.requests.PatientUpdateRequestDTO;
import gestao.sghss.controllers.dtos.responses.PatientResponseDTO;
import gestao.sghss.domain.Patient;
import gestao.sghss.gateways.mapper.AddressMapper;
import gestao.sghss.gateways.mapper.InsuranceMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = { AddressMapper.class, InsuranceMapper.class }
)
public interface PatientControllerMapper {

    Patient toDomain(PatientRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patient toDomain(PatientRequestDTO dto, @MappingTarget Patient domain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patient toDomain(PatientUpdateRequestDTO dto, @MappingTarget Patient domain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patient toDomain(PatientPatchRequestDTO dto);

    PatientResponseDTO toResponse(Patient domain);

    List<Patient> toDomainList(List<PatientRequestDTO> dtos);

    List<PatientResponseDTO> toResponseList(List<Patient> domains);
}
