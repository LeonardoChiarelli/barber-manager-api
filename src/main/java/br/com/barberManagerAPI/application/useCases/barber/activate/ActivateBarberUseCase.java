package br.com.barberManagerAPI.application.useCases.barber.activate;

import br.com.barberManagerAPI.application.ports.out.BarberRepository;
import jakarta.persistence.EntityNotFoundException;

public class ActivateBarberUseCase {

    private final BarberRepository repository;

    public ActivateBarberUseCase(BarberRepository repository) {
        this.repository = repository;
    }

    public void execute(ActivateBarberCommand cmd) {

        var barber = repository.findById(cmd.barberId()).orElseThrow(() -> new EntityNotFoundException("Barber does not exist"));

        if (!barber.isActive()) {
            throw new IllegalStateException("Barber is already deactivated.");
        }

        repository.activateById(cmd.barberId());
        barber.activate();
    }
}
