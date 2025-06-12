package gestao.sghss.gateways.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AddressEmbeddable {

    @Column(name = "zip_code", length = 8)
    private String zipCode;

    @Column(name = "street", length = 255)
    private String street;

    @Column(name = "address_number", length = 10)
    private String number;

    @Column(name = "complement", length = 255)
    private String complement;

    @Column(name = "neighborhood", length = 255)
    private String neighborhood;

    @Column(name = "city", length = 255)
    private String city;

    @Column(name = "state", length = 2)
    private String state;
}

