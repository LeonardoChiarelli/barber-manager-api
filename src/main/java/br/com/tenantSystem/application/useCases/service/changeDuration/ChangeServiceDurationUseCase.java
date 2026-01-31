package br.com.tenantSystem.application.useCases.service.changeDuration;

import br.com.tenantSystem.application.ports.out.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;

public class ChangeServiceDurationUseCase {

    private final ServiceRepository repository;

    public ChangeServiceDurationUseCase(ServiceRepository repository) {
        this.repository = repository;
    }

    public void execute(ChangeServiceDurationCommand cmd) {

        var service = repository.findById(cmd.serviceId()).orElseThrow(() -> new EntityNotFoundException("Service not found"));

        if (service.getDurationMinutes().equals(cmd.newDuration())) {
            throw new IllegalArgumentException("New duration has already been set for this service");
        }

        repository.changeDurationById(cmd.serviceId(), cmd.newDuration());
        service.changeDurationMinutes(cmd.newDuration());
    }
}
