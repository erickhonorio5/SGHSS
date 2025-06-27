package gestao.sghss.controllers;

import gestao.sghss.controllers.dtos.requests.AppointmentCreateRequestDTO;
import gestao.sghss.controllers.dtos.responses.AppointmentResponseDTO;
import gestao.sghss.controllers.mappers.AppointmentControllerMapper;
import gestao.sghss.usecases.AppointmentUseCase.CreateAppointmentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/appointments")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Appointment Controller", description = "Operações de agendamento de consultas")
public class AppointmentController {

    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final AppointmentControllerMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Agendar consulta", description = "Agendar uma nova consulta")
    public AppointmentResponseDTO create(@RequestBody @Valid AppointmentCreateRequestDTO request) {
        return mapper.toResponse(createAppointmentUseCase.execute(mapper.toDomain(request)));
    }
}
