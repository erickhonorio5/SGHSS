package gestao.sghss.usecases.AppointmentUseCase;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.gateways.WorkScheduleGateway;
import gestao.sghss.services.AvailabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListAvailableDaysUseCase {

    private final AvailabilityService availabilityService;
    private final WorkScheduleGateway workScheduleGateway;

    public List<LocalDate> execute(Long professionalId, LocalDate start, LocalDate end) {
        List<WorkSchedule> schedules = workScheduleGateway.findByProfessionalId(professionalId);

        Set<DayOfWeek> workingDays = schedules.stream()
                .map(WorkSchedule::getDayOfWeek)
                .collect(Collectors.toSet());

        List<LocalDate> availableDays = new ArrayList<>();

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            if (workingDays.contains(date.getDayOfWeek())) {
                var availableSlots = availabilityService.listAvailableTimeSlots(professionalId, date);
                if (!availableSlots.isEmpty()) {
                    availableDays.add(date);
                }
            }
        }

        return availableDays;
    }

}
