package br.com.tenantSystem.application.useCases.tenant.deactivate;

import br.com.tenantSystem.application.ports.out.TenantRepository;
import jakarta.persistence.EntityNotFoundException;

public class DeactivateTenantUseCase {

    private final TenantRepository repository;

    public DeactivateTenantUseCase(TenantRepository repository) {
        this.repository = repository;
    }

    public void execute(DeactivateTenantCommand cmd) {

        var tenant = repository.findById(cmd.tenantId()).orElseThrow(() -> new EntityNotFoundException("Tenant not found"));

        repository.deactivateById(cmd.tenantId());
        tenant.deactivate();
    }
}
