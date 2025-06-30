package gestao.sghss.usecases.AppointmentUseCase;

import gestao.sghss.services.AvailabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListAvailableTimeSlotsUseCase {

    private final AvailabilityService availabilityService;

    public List<LocalTime> execute(Long professionalId, LocalDate date) {
        log.info("Buscando horários disponíveis do profissional com ID: {}", professionalId);
        return availabilityService.listAvailableTimeSlots(professionalId, date);
    }
}
