package gestao.sghss.usecases.WorkScheduleUseCase;

import gestao.sghss.domain.WorkSchedule;
import gestao.sghss.exceptions.WorkScheduleConflictException;
import gestao.sghss.exceptions.WorkScheduleValidationException;
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

    public WorkSchedule execute(Long professionalId, WorkSchedule schedule) {
        log.info("Criando horário de trabalho para profissional ID: {}", professionalId);

        professionalGateway.findById(professionalId);

        if (workScheduleGateway.existsByProfessionalIdAndDayOfWeek(professionalId, schedule.getDayOfWeek()))
            throw new WorkScheduleConflictException(schedule.getDayOfWeek());

        validateSchedule(schedule);

        schedule.setProfessionalId(professionalId);
        WorkSchedule created = workScheduleGateway.save(schedule);

        log.info("Horário criado com sucesso para {}: {} - {}", created.getDayOfWeek(), created.getStartTime(), created.getEndTime());

        return created;
    }

    public List<WorkSchedule> createWeek(Long professionalId, List<WorkSchedule> schedules) {
        log.info("Criando agenda da semana inteira para profissional ID: {}", professionalId);

        professionalGateway.findById(professionalId);
        schedules.forEach(s -> s.setProfessionalId(professionalId));

        var saved = workScheduleGateway.saveAll(schedules);

        log.info("Agenda semanal salva com {} horários", saved.size());
        return saved;
    }

    private void validateSchedule(WorkSchedule schedule) {
        if (schedule.getStartTime().isAfter(schedule.getEndTime())) {
            throw new WorkScheduleValidationException("Hora de início deve ser antes da hora de término.");
        }

        if (schedule.getLunchStartTime() != null && schedule.getLunchEndTime() != null) {
            if (schedule.getLunchStartTime().isAfter(schedule.getLunchEndTime())) {
                throw new WorkScheduleValidationException("Início do almoço deve ser antes do fim.");
            }

            if (schedule.getLunchStartTime().isBefore(schedule.getStartTime()) ||
                    schedule.getLunchEndTime().isAfter(schedule.getEndTime())) {
                throw new WorkScheduleValidationException("Almoço deve estar dentro do horário de expediente.");
            }
        }
    }
}


