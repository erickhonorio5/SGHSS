package gestao.sghss.usecases.WorkScheduleUseCase;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.domain.validator.WorkScheduleValidator;
import gestao.sghss.gateways.ProfessionalGateway;
import gestao.sghss.gateways.WorkScheduleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateWorkScheduleUseCase {

    private final ProfessionalGateway professionalGateway;
    private final WorkScheduleGateway workScheduleGateway;
    private final WorkScheduleValidator validator;

    public WorkSchedule execute(Long professionalId, WorkSchedule schedule) {
        log.info("Criando horário de trabalho para profissional ID: {}", professionalId);

        professionalGateway.findById(professionalId);

        var existingSchedules = workScheduleGateway.findByProfessionalId(professionalId);

        validator.validateNoDuplicateDay(schedule.getDayOfWeek(), professionalId, null, existingSchedules);
        validator.validateTimes(
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getLunchStartTime(),
                schedule.getLunchEndTime()
        );

        schedule.setProfessionalId(professionalId);
        WorkSchedule created = workScheduleGateway.save(schedule);

        log.info("Horário criado com sucesso para {}: {} - {}", created.getDayOfWeek(), created.getStartTime(), created.getEndTime());

        return created;
    }

    public List<WorkSchedule> createWeek(Long professionalId, List<WorkSchedule> schedules) {
        log.info("Criando agenda da semana inteira para profissional ID: {}", professionalId);

        professionalGateway.findById(professionalId);

        var existingSchedules = workScheduleGateway.findByProfessionalId(professionalId);

        for (WorkSchedule schedule : schedules) {
            validator.validateNoDuplicateDay(schedule.getDayOfWeek(), professionalId, null, existingSchedules);
            validator.validateTimes(
                    schedule.getStartTime(),
                    schedule.getEndTime(),
                    schedule.getLunchStartTime(),
                    schedule.getLunchEndTime()
            );
            schedule.setProfessionalId(professionalId);
        }

        var saved = workScheduleGateway.saveAll(schedules);

        log.info("Agenda semanal salva com {} horários", saved.size());
        return saved;
    }
}


