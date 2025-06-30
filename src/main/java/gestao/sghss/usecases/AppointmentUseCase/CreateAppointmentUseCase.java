package gestao.sghss.usecases.AppointmentUseCase;

import gestao.sghss.domain.Appointment;
import gestao.sghss.domain.enums.AppointmentStatus;
import gestao.sghss.domain.enums.AppointmentType;
import gestao.sghss.domain.validator.AppointmentValidator;
import gestao.sghss.exceptions.AppointmentSlotUnavailableException;
import gestao.sghss.gateways.AppointmentGateway;
import gestao.sghss.gateways.PatientGateway;
import gestao.sghss.gateways.ProfessionalGateway;
import gestao.sghss.services.AvailabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateAppointmentUseCase {

    private final AppointmentGateway appointmentGateway;
    private final PatientGateway patientGateway;
    private final ProfessionalGateway professionalGateway;
    private final AppointmentValidator appointmentValidator;
    private final AvailabilityService service;

    public Appointment execute(Appointment appointment) {
        log.info("Iniciando criação de consulta para paciente {} e profissional {} no dia {} às {}",
                appointment.getPatientId(), appointment.getProfessionalId(),
                appointment.getAppointmentDate(), appointment.getAppointmentTime());

        appointmentValidator.validateAppointmentDateTime(
                appointment.getAppointmentDate(), appointment.getAppointmentTime()
        );
        appointmentValidator.validateObservations(appointment.getObservations());

        patientGateway.findById(appointment.getPatientId());
        professionalGateway.findById(appointment.getProfessionalId());

        service.validateAvailability(
                appointment.getProfessionalId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime()
        );

        if (appointment.getAppointmentStatus() == null) {
            appointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);
        }

        if (appointment.getAppointmentType() == null) {
            appointment.setAppointmentType(AppointmentType.IN_PERSON);
        }

        Appointment created = appointmentGateway.save(appointment);

        log.info("Consulta criada com sucesso, id: {}", created.getAppointmentId());
        return created;
    }
}



