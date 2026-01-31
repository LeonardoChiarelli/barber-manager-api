package br.com.tenantSystem.application.useCases.barber.create;

import br.com.tenantSystem.application.ports.out.BarberRepository;
import br.com.tenantSystem.application.ports.out.TenantRepository;
import br.com.tenantSystem.domain.barber.Barber;

public class CreateBarberUseCase {

    private final BarberRepository repository;
    private final TenantRepository tenantRepository;

    public CreateBarberUseCase(BarberRepository repository, TenantRepository tenantRepository) {
        this.repository = repository;
        this.tenantRepository = tenantRepository;
    }

    public CreateBarberResult execute(CreateBarberCommand cmd) {

        var existsTenant = tenantRepository.existsById(cmd.tenantId());
        if (!existsTenant) {
            throw new IllegalArgumentException("Tenant does not exist");
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
