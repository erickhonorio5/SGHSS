package gestao.sghss.gateways.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class InsuranceEmbeddable {

    @Column(name = "insurance_name", length = 255)
    private String name;

    @Column(name = "insurance_number", length = 50)
    private String number;

    @Column(name = "insurance_expiry_date")
    private LocalDate expiryDate;
}

