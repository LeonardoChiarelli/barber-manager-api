package br.com.tenantSystem.application.useCases.barber.delete;

import br.com.tenantSystem.application.ports.out.BarberRepository;
import jakarta.persistence.EntityNotFoundException;

public class DeleteBarberUseCase {

    private final BarberRepository repository;

    public DeleteBarberUseCase(BarberRepository repository) {
        this.repository = repository;
    }

    public void execute(DeleteBarberCommand cmd) {

        var barber = repository.findById(cmd.barberId()).orElseThrow(() -> new EntityNotFoundException("Barber does not exist"));

        repository.deleteById(cmd.barberId());
    }
}
