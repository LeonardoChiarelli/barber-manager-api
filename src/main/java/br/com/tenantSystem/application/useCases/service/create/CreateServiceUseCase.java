package br.com.tenantSystem.application.useCases.service.create;

import br.com.tenantSystem.application.ports.out.ServiceRepository;
import br.com.tenantSystem.application.ports.out.TenantRepository;
import br.com.tenantSystem.domain.service.Service;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;


public class CreateServiceUseCase {

    private final ServiceRepository repository;
    private final TenantRepository tenantRepository;

    public CreateServiceUseCase(ServiceRepository repository, TenantRepository tenantRepository) {
        this.repository = repository;
        this.tenantRepository = tenantRepository;
    }

    public CreateServiceResult execute(CreateServiceCommand cmd) {

        var existsTenant = tenantRepository.existsById(cmd.tenantId());
        if (!existsTenant) {
            throw new EntityNotFoundException("Tenant not found");
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
