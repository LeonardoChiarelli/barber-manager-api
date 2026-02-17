package br.com.tenantSystem.application.useCases.scheduling.confirm;

import br.com.tenantSystem.application.ports.out.SchedulingRepository;
import br.com.tenantSystem.application.useCases.exception.BusinessRules;
import br.com.tenantSystem.domain.scheduling.SchedulingStatus;
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
