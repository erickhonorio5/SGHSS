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

    // Converte Request DTO para Domain (novo registro)
    Patient toDomain(PatientRequestDTO dto);

    // Atualiza uma instância de domínio existente com dados do DTO (opcional)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patient toDomain(PatientRequestDTO dto, @MappingTarget Patient domain);

    // Atualiza dados do paciente com DTO de atualização
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patient toDomain(PatientUpdateRequestDTO dto, @MappingTarget Patient domain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patient toDomain(PatientPatchRequestDTO dto);

    // Converte Domain para Response DTO
    PatientResponseDTO toResponse(Patient domain);

    List<Patient> toDomainList(List<PatientRequestDTO> dtos);

    List<PatientResponseDTO> toResponseList(List<Patient> domains);
}
