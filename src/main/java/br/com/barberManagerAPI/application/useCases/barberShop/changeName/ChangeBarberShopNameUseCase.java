package br.com.barberManagerAPI.application.useCases.barberShop.changeName;

import br.com.barberManagerAPI.application.ports.out.BarberShopRepository;
import jakarta.persistence.EntityNotFoundException;

public class ChangeBarberShopNameUseCase {

    private final BarberShopRepository repository;

    public ChangeBarberShopNameUseCase(BarberShopRepository repository) {
        this.repository = repository;
    }

    public void execute(ChangeBarberShopNameCommand cmd) {

        var tenant = repository.findById(cmd.tenantId()).orElseThrow(() -> new EntityNotFoundException("BarberShop not found"));

        if (tenant.getName().equals(cmd.newName())) {
            throw new IllegalArgumentException("BarberShop's old name is equals to new name");
        }

        repository.changeNameById(cmd.tenantId(), cmd.newName());
        tenant.changeName(cmd.newName());
    }
}
