package gestao.sghss.controllers.dtos.requests;

import gestao.sghss.controllers.dtos.AddressDTO;
import gestao.sghss.controllers.dtos.InsuranceDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientUpdateRequestDTO {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    private String phone;

    @Past(message = "Birthdate must be in the past")
    private LocalDate birthDate;

    private AddressDTO address;

    private InsuranceDTO insurance;
}
