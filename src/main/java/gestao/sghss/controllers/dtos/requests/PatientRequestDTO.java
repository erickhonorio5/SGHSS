package gestao.sghss.controllers.dtos.requests;

import gestao.sghss.controllers.dtos.AddressDTO;
import gestao.sghss.controllers.dtos.InsuranceDTO;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
public class PatientRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    @CPF
    private String cpf;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^\\d{11}$",
            message = "Telefone inválido. Use somente números no formato DDD + número (ex: 11999999999)"
    )
    @Size(min = 11, max = 11, message = "Telefone deve ter exatamente 11 caracteres")
    private String phone;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private AddressDTO address;

    private InsuranceDTO insurance;
}
