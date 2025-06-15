package gestao.sghss.controllers.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InsuranceDTO {
    private String name;
    private String number;
    private LocalDate expiryDate;
}
