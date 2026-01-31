package br.com.tenantSystem.application.useCases.tenant.delete;

import br.com.tenantSystem.application.ports.out.TenantRepository;
import jakarta.persistence.EntityNotFoundException;

public class DeleteTenantUseCase {

    private final TenantRepository repository;

    public DeleteTenantUseCase(TenantRepository repository) {
        this.repository = repository;
    }

    public void execute(DeleteTenantCommand cmd) {

        var tenant = repository.findById(cmd.tenantId()).orElseThrow(() -> new EntityNotFoundException("Tenant does not exist"));

        repository.deleteById(cmd.tenantId());
    }
}
