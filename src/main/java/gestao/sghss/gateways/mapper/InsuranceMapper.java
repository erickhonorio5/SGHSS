package gestao.sghss.gateways.mapper;

import gestao.sghss.domain.Insurance;
import gestao.sghss.gateways.entities.InsuranceEmbeddable;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InsuranceMapper {

    Insurance toDomain(InsuranceEmbeddable embeddable);

    InsuranceEmbeddable toEmbeddable(Insurance domain);
}

