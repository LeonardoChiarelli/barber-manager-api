package br.com.barberManagerAPI.application.useCases.scheduling.confirm;

import br.com.barberManagerAPI.application.ports.out.SchedulingRepository;
import br.com.barberManagerAPI.application.useCases.exception.BusinessRules;
import br.com.barberManagerAPI.domain.scheduling.SchedulingStatus;
import jakarta.persistence.EntityNotFoundException;

public class ConfirmSchedulingUseCase {
    private final SchedulingRepository repository;

    public ConfirmSchedulingUseCase(SchedulingRepository schedulingRepository) {
        this.repository = schedulingRepository;
    }

    public void execute(ConfirmSchedulingCommand cmd) {

        var schedule = repository.findById(cmd.schedulingId()).orElseThrow(() -> new EntityNotFoundException("Schedule not found"));

        if (schedule.getStatus() != SchedulingStatus.PENDING) {
            throw new BusinessRules("Only pending confirmation can be confirmed");
        }

        repository.changeStatus(schedule.getId(), SchedulingStatus.CONFIRMED);
        schedule.changeStatus(SchedulingStatus.CONFIRMED);
    }
}
