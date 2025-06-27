package gestao.sghss.repositories;

import gestao.sghss.domain.enums.AppointmentStatus;
import gestao.sghss.gateways.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> findByPatientId(Long patientId);

    List<AppointmentEntity> findByProfessionalId(Long professionalId);

    List<AppointmentEntity> findByAppointmentStatus(AppointmentStatus status);

    List<AppointmentEntity> findByAppointmentDateBetween(LocalDate start, LocalDate end);

    boolean existsByProfessionalIdAndAppointmentDateAndAppointmentTime(Long professionalId, LocalDate appointmentDate, LocalTime appointmentTime);

    List<AppointmentEntity> findByProfessionalIdAndAppointmentDateBetween(Long professionalId, LocalDate start, LocalDate end);
}