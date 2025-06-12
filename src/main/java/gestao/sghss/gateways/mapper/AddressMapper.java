package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.Address;
import gestao.sghss.gateways.entities.AddressEmbeddable;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface AddressMapper {

    Address toDomain(AddressEmbeddable addressEmbeddable);

    AddressEmbeddable toEmbeddable(Address address);
}
