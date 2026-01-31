package br.com.tenantSystem.application.useCases.tenant.create;

import br.com.tenantSystem.application.ports.out.TenantRepository;
import br.com.tenantSystem.domain.tenant.Tenant;

public class CreateTenantUseCase {

    private final TenantRepository repository;

    public CreateTenantUseCase(TenantRepository repository) {
        this.repository = repository;
    }

    public CreateTenantResult execute(CreateTenantCommand cmd) {

        var existsTenant = repository.existsByNameAndTimezone(cmd.name(), cmd.timezone());
        if (existsTenant) {
            throw new IllegalArgumentException("Tenant already exists");
        }

        var tenant = Tenant.builder()
                .name(cmd.name())
                .timezone(cmd.timezone())
                .build();

        repository.save(tenant);
        return new CreateTenantResult(tenant.getId(), tenant.getName(), tenant.getTimezone(), tenant.isActive());
    }
}
