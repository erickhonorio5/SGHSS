package gestao.sghss.usecases.AppointmentUseCase;

import gestao.sghss.exceptions.WorkScheduleException;
import gestao.sghss.services.AvailabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListAvailableSlotsByDayUseCase {

    private final AvailabilityService availabilityService;

    public Map<LocalDate, List<LocalTime>> execute(Long professionalId, LocalDate start, LocalDate end) {
        Map<LocalDate, List<LocalTime>> result = new LinkedHashMap<>();

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            try {
                List<LocalTime> slots = availabilityService.listAvailableTimeSlots(professionalId, date);
                if (!slots.isEmpty()) {
                    result.put(date, slots);
                }
            } catch (WorkScheduleException e) {
                // TODO Profissional não trabalha nesse dia — ignorar
            }
        }

        return result;
    }
}
