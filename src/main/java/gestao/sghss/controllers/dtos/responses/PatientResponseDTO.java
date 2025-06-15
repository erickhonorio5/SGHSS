package gestao.sghss.controllers.dtos.responses;

import gestao.sghss.controllers.dtos.AddressDTO;
import gestao.sghss.controllers.dtos.InsuranceDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientResponseDTO {

    private Long id;

    private String name;
    private String cpf;
    private String email;
    private String phone;
    private LocalDate birthDate;

    private AddressDTO address;
    private InsuranceDTO insurance;

    private Boolean active;
}
