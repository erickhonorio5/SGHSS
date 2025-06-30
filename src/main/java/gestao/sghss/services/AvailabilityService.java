package gestao.sghss.services;

import gestao.sghss.domain.Appointment;
import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.exceptions.AppointmentSlotUnavailableException;
import gestao.sghss.exceptions.WorkScheduleException;
import gestao.sghss.gateways.AppointmentGateway;
import gestao.sghss.gateways.WorkScheduleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private static final Duration SLOT_DURATION = Duration.ofMinutes(30);

    private final WorkScheduleGateway workScheduleGateway;
    private final AppointmentGateway appointmentGateway;

    public void validateAvailability(Long professionalId, LocalDate date, LocalTime time) {
        var dayOfWeek = date.getDayOfWeek();

        var workSchedule = workScheduleGateway.findByProfessionalId(professionalId).stream()
                .filter(schedule -> schedule.getDayOfWeek().equals(dayOfWeek))
                .findFirst()
                .orElseThrow(() -> new WorkScheduleException("Profissional não trabalha nesse dia da semana"));

        if (!isWithinWorkingHours(workSchedule, time)) {
            throw new AppointmentSlotUnavailableException("Horário fora do expediente do profissional");
        }

        if (appointmentGateway.existsByProfessionalIdAndAppointmentDateAndAppointmentTime(professionalId, date, time)) {
            throw new AppointmentSlotUnavailableException("Horário já ocupado");
        }

        if (hasConflictingAppointments(professionalId, date, time)) {
            throw new AppointmentSlotUnavailableException("Horário conflita com outra consulta e intervalo de descanso mínimo");
        }
    }

    private boolean isWithinWorkingHours(WorkSchedule schedule, LocalTime time) {
        boolean isInMorning = !time.isBefore(schedule.getStartTime()) && time.plus(SLOT_DURATION).isBefore(schedule.getLunchStartTime());
        boolean isInAfternoon = time.isAfter(schedule.getLunchEndTime()) && !time.plus(SLOT_DURATION).isAfter(schedule.getEndTime());
        return isInMorning || isInAfternoon;
    }

    private boolean hasConflictingAppointments(Long professionalId, LocalDate date, LocalTime requestedStart) {
        var appointments = appointmentGateway.findByProfessionalIdAndDate(professionalId, date);

        LocalTime requestedEnd = requestedStart.plus(SLOT_DURATION);

        for (Appointment existing : appointments) {
            LocalTime existingStart = existing.getAppointmentTime();
            LocalTime existingEnd = existingStart.plus(SLOT_DURATION);

            boolean overlaps = requestedStart.isBefore(existingEnd) && requestedEnd.isAfter(existingStart);

            if (overlaps) return true;
        }

        return false;
    }

    public List<LocalTime> listAvailableTimeSlots(Long professionalId, LocalDate date) {
        var dayOfWeek = date.getDayOfWeek();

        var schedule = workScheduleGateway.findByProfessionalId(professionalId).stream()
                .filter(ws -> ws.getDayOfWeek().equals(dayOfWeek))
                .findFirst()
                .orElseThrow(() -> new WorkScheduleException("Profissional não trabalha nesse dia"));

        List<Appointment> appointments = appointmentGateway.findByProfessionalIdAndDate(professionalId, date);

        Set<LocalTime> takenTimes = appointments.stream()
                .map(Appointment::getAppointmentTime)
                .collect(Collectors.toSet());

        return generateTimeSlots(schedule, takenTimes);
    }

    private List<LocalTime> generateTimeSlots(WorkSchedule schedule, Set<LocalTime> takenTimes) {
        List<LocalTime> slots = new ArrayList<>();

        generateRange(slots, schedule.getStartTime(), schedule.getLunchStartTime(), takenTimes);
        generateRange(slots, schedule.getLunchEndTime(), schedule.getEndTime(), takenTimes);

        return slots;
    }

    private void generateRange(List<LocalTime> slots, LocalTime start, LocalTime end, Set<LocalTime> takenTimes) {
        LocalTime current = start;
        while (!current.plus(SLOT_DURATION).isAfter(end)) {
            if (!takenTimes.contains(current)) {
                slots.add(current);
            }
            current = current.plus(SLOT_DURATION);
        }
    }
}
