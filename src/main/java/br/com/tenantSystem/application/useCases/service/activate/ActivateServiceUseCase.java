package br.com.tenantSystem.application.useCases.service.activate;

import br.com.tenantSystem.application.ports.out.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;

public class ActivateServiceUseCase {

    private final ServiceRepository repository;

    public ActivateServiceUseCase(ServiceRepository repository) {
        this.repository = repository;
    }

    public void execute(ActivateServiceCommand cmd) {

        var service = repository.findById(cmd.serviceId()).orElseThrow(() -> new EntityNotFoundException("Service not found"));

        if (service.isActive()) {
            throw new IllegalStateException("Service is already active.");
        }

        repository.activateById(cmd.serviceId());
        service.activate();
    }
}
