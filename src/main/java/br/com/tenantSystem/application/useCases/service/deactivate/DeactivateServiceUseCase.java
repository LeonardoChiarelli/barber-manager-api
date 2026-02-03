package br.com.tenantSystem.application.useCases.service.deactivate;

import br.com.tenantSystem.application.ports.out.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;

public class DeactivateServiceUseCase {

    private final ServiceRepository repository;

    public DeactivateServiceUseCase(ServiceRepository repository) {
        this.repository = repository;
    }

    public void execute(DeactivateServiceCommand cmd) {

        var service = repository.findById(cmd.serviceId()).orElseThrow(() -> new EntityNotFoundException("Service not found"));

        repository.deactivateById(cmd.serviceId());
        service.deactivate();
    }
}
