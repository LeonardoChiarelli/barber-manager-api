package br.com.barberManagerAPI.application.useCases.service.changeName;

import br.com.barberManagerAPI.application.ports.out.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;

public class ChangeServiceNameUseCase {

    private final ServiceRepository repository;

    public ChangeServiceNameUseCase(ServiceRepository repository) {
        this.repository = repository;
    }

    public void execute(ChangeServiceNameCommand cmd) {

        var service = repository.findById(cmd.serviceId()).orElseThrow(() -> new EntityNotFoundException("Service not found"));

        if (service.getName().equals(cmd.newName())) {
            throw new IllegalArgumentException("Service's old name is equals to new name");
        }

        repository.changeNameById(cmd.serviceId(), cmd.newName());
        service.changeName(cmd.newName());
    }
}
