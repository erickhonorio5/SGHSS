package gestao.sghss.gateways;

import gestao.sghss.domain.Appointment;
import gestao.sghss.domain.enums.AppointmentStatus;
import gestao.sghss.exceptions.AppointmentNotFoundException;
import gestao.sghss.gateways.mapper.AppointmentMapper;
import gestao.sghss.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentGateway {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public Appointment save(Appointment appointment) {
        return appointmentMapper.toDomain(appointmentRepository.save(appointmentMapper.toEntity(appointment)));
    }

    public void deleteById(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    public Appointment findById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).map(appointmentMapper::toDomain).orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
    }

    public List<Appointment> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId).stream().map(appointmentMapper::toDomain).toList();
    }

    public List<Appointment> findByProfessionalId(Long professionalId) {
        return appointmentRepository.findByProfessionalId(professionalId).stream().map(appointmentMapper::toDomain).toList();
    }

    public List<Appointment> findByStatus(AppointmentStatus status) {
        return appointmentRepository.findByAppointmentStatus(status).stream().map(appointmentMapper::toDomain).toList();
    }

    public List<Appointment> findByDateRange(LocalDate start, LocalDate end) {
        return appointmentRepository.findByAppointmentDateBetween(start, end)
                .stream()
                .map(appointmentMapper::toDomain)
                .toList();
    }

    public List<Appointment> findByProfessionalIdAndDate(Long professionalId, LocalDate appointmentDate) {
        return appointmentRepository.findByProfessionalIdAndAppointmentDate(professionalId, appointmentDate)
                .stream()
                .map(appointmentMapper::toDomain)
                .toList();
    }

    public boolean existsByProfessionalIdAndAppointmentDateAndAppointmentTime(Long professionalId, LocalDate appointmentDate, LocalTime appointmentTime) {
        return appointmentRepository.existsByProfessionalIdAndAppointmentDateAndAppointmentTime(professionalId, appointmentDate, appointmentTime);
    }
}
