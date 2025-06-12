package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.Patient;
import gestao.sghss.gateways.entities.PatientEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, InsuranceMapper.class})
public interface PatientMapper {

    Patient toDomain(PatientEntity entity);

    PatientEntity toEntity(Patient domain);

    List<Patient> toDomainList(List<PatientEntity> entities);

    List<PatientEntity> toEntityList(List<Patient> domains);
}
