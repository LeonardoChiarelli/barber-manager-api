package br.com.barberManagerAPI.application.useCases.barber.create;

import br.com.barberManagerAPI.application.ports.out.BarberRepository;
import br.com.barberManagerAPI.application.ports.out.BarberShopRepository;
import br.com.barberManagerAPI.domain.barber.Barber;

public class CreateBarberUseCase {

    private final BarberRepository repository;
    private final BarberShopRepository barberShopRepository;

    public CreateBarberUseCase(BarberRepository repository, BarberShopRepository barberShopRepository) {
        this.repository = repository;
        this.barberShopRepository = barberShopRepository;
    }

    public CreateBarberResult execute(CreateBarberCommand cmd) {

        var existsTenant = barberShopRepository.existsById(cmd.tenantId());
        if (!existsTenant) {
            throw new IllegalArgumentException("BarberShop does not exist");
        }

        var existsBarber = repository.existsByBarberNameAndNickname(cmd.name(), cmd.nickname());
        if (existsBarber) {
            throw new IllegalArgumentException("Barber already exists");
        }

        var barber = Barber.builder()
                .name(cmd.name())
                .tenantId(cmd.tenantId())
                .nickname(cmd.nickname())
                .active(cmd.active())
                .build();

        repository.save(barber);
        return new CreateBarberResult(barber.getId(), barber.getName(), barber.getNickname(), barber.isActive());
    }
}
