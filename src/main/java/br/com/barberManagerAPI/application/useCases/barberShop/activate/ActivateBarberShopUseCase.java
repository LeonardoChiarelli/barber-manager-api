package br.com.barberManagerAPI.application.useCases.barberShop.activate;

import br.com.barberManagerAPI.application.ports.out.BarberShopRepository;
import jakarta.persistence.EntityNotFoundException;

public class ActivateBarberShopUseCase {

    private final BarberShopRepository repository;

    public ActivateBarberShopUseCase(BarberShopRepository repository) {
        this.repository = repository;
    }

    public void execute(ActivateBarberShopCommand cmd) {

        var tenant = repository.findById(cmd.tenantId()).orElseThrow(() -> new EntityNotFoundException("BarberShop not found"));

        if (tenant.isActive()) {
            throw new IllegalStateException("BarberShop is already active.");
        }

        repository.activateById(cmd.tenantId());
        tenant.activate();
    }
}
