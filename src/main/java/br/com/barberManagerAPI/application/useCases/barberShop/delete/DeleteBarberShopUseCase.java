package br.com.barberManagerAPI.application.useCases.barberShop.delete;

import br.com.barberManagerAPI.application.ports.out.BarberShopRepository;
import jakarta.persistence.EntityNotFoundException;

public class DeleteBarberShopUseCase {

    private final BarberShopRepository repository;

    public DeleteBarberShopUseCase(BarberShopRepository repository) {
        this.repository = repository;
    }

    public void execute(DeleteBarberShopCommand cmd) {

        var tenant = repository.findById(cmd.tenantId()).orElseThrow(() -> new EntityNotFoundException("BarberShop does not exist"));

        repository.deleteById(cmd.tenantId());
    }
}
