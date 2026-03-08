package br.com.barberManagerAPI.application.useCases.service.create;

import br.com.barberManagerAPI.application.ports.out.ServiceRepository;
import br.com.barberManagerAPI.application.ports.out.BarberShopRepository;
import br.com.barberManagerAPI.domain.service.Service;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;


public class CreateServiceUseCase {

    private final ServiceRepository repository;
    private final BarberShopRepository barberShopRepository;

    public CreateServiceUseCase(ServiceRepository repository, BarberShopRepository barberShopRepository) {
        this.repository = repository;
        this.barberShopRepository = barberShopRepository;
    }

    public CreateServiceResult execute(CreateServiceCommand cmd) {

        var existsTenant = barberShopRepository.existsById(cmd.tenantId());
        if (!existsTenant) {
            throw new EntityNotFoundException("BarberShop not found");
        }

        var existsService = repository.existsByNameAndTenantId(cmd.name(), cmd.tenantId());
        if (existsService) {
            throw new EntityExistsException("Service already exists");
        }

        var service = Service.builder()
                .name(cmd.name())
                .tenantId(cmd.tenantId())
                .price(cmd.price())
                .durationMinutes(cmd.durationMinutes())
                .active(cmd.active())
                .build();

        repository.save(service);
        return new CreateServiceResult(service.getId(), service.getName(), service.getDurationMinutes(), service.getPrice(), service.isActive());
    }
}
