package br.com.barberManagerAPI.application.useCases.barberShop.deactivate;

import br.com.barberManagerAPI.application.ports.out.BarberShopRepository;
import jakarta.persistence.EntityNotFoundException;

public class DeactivateBarberShopUseCase {

    private final BarberShopRepository repository;

    public DeactivateBarberShopUseCase(BarberShopRepository repository) {
        this.repository = repository;
    }

    public void execute(DeactivateBarberShopCommand cmd) {

        var tenant = repository.findById(cmd.tenantId()).orElseThrow(() -> new EntityNotFoundException("BarberShop not found"));

        repository.deactivateById(cmd.tenantId());
        tenant.deactivate();
    }
}
