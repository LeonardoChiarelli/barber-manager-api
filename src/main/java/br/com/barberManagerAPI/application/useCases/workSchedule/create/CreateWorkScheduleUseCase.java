package br.com.barberManagerAPI.application.useCases.workSchedule.create;

import br.com.barberManagerAPI.application.ports.out.BarberRepository;
import br.com.barberManagerAPI.application.ports.out.WorkScheduleRepository;
import br.com.barberManagerAPI.application.useCases.exception.BusinessRules;
import br.com.barberManagerAPI.domain.barber.WorkSchedule;

public class CreateWorkScheduleUseCase {

    private final WorkScheduleRepository repository;
    private final BarberRepository barberRepository;

    public CreateWorkScheduleUseCase(WorkScheduleRepository repository, BarberRepository barberRepository) {
        this.repository = repository;
        this.barberRepository = barberRepository;
    }

    public void execute(CreateWorkScheduleCommand cmd) {
        var barber = barberRepository.findById(cmd.barberId()).orElseThrow(() -> new BusinessRules("Barber not found"));

        var existsWorkSchedule = repository.existsByIdAndWeekDay(cmd.barberId(), cmd.weekDay());
        if (existsWorkSchedule) {
            throw new BusinessRules("Barber already has a work schedule set");
        }

        var workSchedule = WorkSchedule.builder()
                .barberId(barber.getId())
                .weekDay(cmd.weekDay())
                .startTime(cmd.startTime())
                .endTime(cmd.endTime())
                .intervalStart(cmd.intervalStart())
                .intervalEnd(cmd.intervalEnd())
                .build();

        repository.save(workSchedule);
    }
}
