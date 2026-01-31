package br.com.tenantSystem.application.useCases.barber.deactivate;

import br.com.tenantSystem.application.ports.out.BarberRepository;
import jakarta.persistence.EntityNotFoundException;

public class DeactivateBarberUseCase {

    private final BarberRepository repository;

    public DeactivateBarberUseCase(BarberRepository repository) {
        this.repository = repository;
    }

    public void execute(DeactivateBarberCommand cmd) {

        var barber = repository.findById(cmd.barberId()).orElseThrow(() -> new EntityNotFoundException("Barber does not exist"));

        repository.deactivateById(cmd.barberId());
        barber.deactivate();
    }
}
