package br.com.barberManagerAPI.application.useCases.service.delete;

import br.com.barberManagerAPI.application.ports.out.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;

public class DeleteServiceUseCase {

    private final ServiceRepository repository;

    public DeleteServiceUseCase(ServiceRepository repository) {
        this.repository = repository;
    }

    public void execute(DeleteServiceCommand cmd) {

        var service = repository.findById(cmd.serviceId()).orElseThrow(() -> new EntityNotFoundException("Service not found"));

        repository.deleteById(cmd.serviceId());
    }
}
