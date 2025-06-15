package gestao.sghss.controllers.mappers;

import gestao.sghss.controllers.dtos.AddressDTO;
import gestao.sghss.domain.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressControllerMapper {

    Address toDomain(AddressDTO dto);

    AddressDTO toDto(Address domain);

    List<Address> toDomainList(List<AddressDTO> dtos);

    List<AddressDTO> toDtoList(List<Address> domains);
}