package br.com.barberManagerAPI.application.useCases.workSchedule.changeIntervalTime;

import br.com.barberManagerAPI.application.ports.out.SchedulingRepository;
import br.com.barberManagerAPI.application.ports.out.WorkScheduleRepository;
import br.com.barberManagerAPI.application.useCases.exception.BusinessRules;
import jakarta.persistence.EntityNotFoundException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class ChangeIntervalTimeUseCase {

    private final WorkScheduleRepository repository;
    private final SchedulingRepository schedulingRepository;

    public ChangeIntervalTimeUseCase(WorkScheduleRepository repository, SchedulingRepository schedulingRepository) {
        this.repository = repository;
        this.schedulingRepository = schedulingRepository;
    }

    public void execute(ChangeIntervalTimeCommand cmd) {

        var workSchedule = repository.findByBarberIdAndWorkDay(cmd.barberId(), cmd.weekDay()).orElseThrow(() -> new EntityNotFoundException("Work schedule was not found."));

        var date = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.of(workSchedule.getWeekDay())));

        var scheduling = schedulingRepository.existsByBarberIdAndStartTimeAndEndTime(cmd.barberId(), date.atTime(cmd.newStartTime()), date.atTime(cmd.newEndTime()));

        if (scheduling) {
            throw new BusinessRules("You can't change the interval time, because you have a scheduling at this time");
        }

        repository.changeIntervalTime(workSchedule.getId(), cmd.newStartTime(), cmd.newEndTime());
        workSchedule.changeIntervalStart(cmd.newStartTime());
        workSchedule.changeIntervalEnd(cmd.newEndTime());

    }
}
