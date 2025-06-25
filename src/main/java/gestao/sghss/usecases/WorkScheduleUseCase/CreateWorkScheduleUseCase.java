package gestao.sghss.usecases.WorkScheduleUseCase;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.gateways.WorkScheduleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateWorkScheduleUseCase {

    private final WorkScheduleGateway gateway;

    public WorkSchedule execute(WorkSchedule schedule) {

        return gateway.save(schedule);
    }
}
