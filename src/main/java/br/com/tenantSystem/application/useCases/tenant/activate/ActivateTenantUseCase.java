package br.com.tenantSystem.application.useCases.tenant.activate;

import br.com.tenantSystem.application.ports.out.TenantRepository;
import jakarta.persistence.EntityNotFoundException;

public class ActivateTenantUseCase {

    private final TenantRepository repository;

    public ActivateTenantUseCase(TenantRepository repository) {
        this.repository = repository;
    }

    public void execute(ActivateTenantCommand cmd) {

        var tenant = repository.findById(cmd.tenantId()).orElseThrow(() -> new EntityNotFoundException("Tenant not found"));

        if (tenant.isActive()) {
            throw new IllegalStateException("Tenant is already active.");
        }

        repository.activateById(cmd.tenantId());
        tenant.activate();
    }
}
