package br.com.barberManagerAPI.application.useCases.workSchedule.delete;

import br.com.barberManagerAPI.application.ports.out.SchedulingRepository;
import br.com.barberManagerAPI.application.ports.out.WorkScheduleRepository;
import br.com.barberManagerAPI.application.useCases.exception.BusinessRules;
import jakarta.persistence.EntityNotFoundException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DeleteWorkScheduleUseCase {

    private final WorkScheduleRepository repository;
    private final SchedulingRepository schedulingRepository;

    public DeleteWorkScheduleUseCase(WorkScheduleRepository repository, SchedulingRepository schedulingRepository) {
        this.repository = repository;
        this.schedulingRepository = schedulingRepository;
    }

    public void execute(DeleteWorkScheduleCommand cmd) {

        var workSchedule = repository.findByBarberIdAndWorkDay(cmd.barberId(), cmd.weekDay()).orElseThrow(() -> new EntityNotFoundException("WorkSchedule not found"));

        var date = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.of(workSchedule.getWeekDay())));

        var scheduling = schedulingRepository.existsByBarberIdAndStartTimeBetween(cmd.barberId(), date.atTime(workSchedule.getStartTime()), date.atTime(workSchedule.getEndTime()));

        if (scheduling) {
            throw new BusinessRules("You cannot delete this work schedule. Because you have a schedule at this day");
        }

        repository.deleteById(workSchedule.getId());
    }
}
