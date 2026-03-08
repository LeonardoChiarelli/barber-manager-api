package br.com.barberManagerAPI.application.useCases.barberShop.create;

import br.com.barberManagerAPI.application.ports.out.BarberShopRepository;
import br.com.barberManagerAPI.domain.barberShop.BarberShop;

public class CreateBarberShopUseCase {

    private final BarberShopRepository repository;

    public CreateBarberShopUseCase(BarberShopRepository repository) {
        this.repository = repository;
    }

    public CreateBarberShopResult execute(CreateBarberShopCommand cmd) {

        var existsTenant = repository.existsByNameAndTimezone(cmd.name(), cmd.timezone());
        if (existsTenant) {
            throw new IllegalArgumentException("BarberShop already exists");
        }

        var tenant = BarberShop.builder()
                .name(cmd.name())
                .timezone(cmd.timezone())
                .build();

        repository.save(tenant);
        return new CreateBarberShopResult(tenant.getId(), tenant.getName(), tenant.getTimezone(), tenant.isActive());
    }
}
