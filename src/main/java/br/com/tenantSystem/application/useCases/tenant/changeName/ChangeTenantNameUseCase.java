package br.com.tenantSystem.application.useCases.tenant.changeName;

import br.com.tenantSystem.application.ports.out.TenantRepository;
import jakarta.persistence.EntityNotFoundException;

public class ChangeTenantNameUseCase {

    private final TenantRepository repository;

    public ChangeTenantNameUseCase(TenantRepository repository) {
        this.repository = repository;
    }

    public void execute(ChangeTenantNameCommand cmd) {

        var tenant = repository.findById(cmd.tenantId()).orElseThrow(() -> new EntityNotFoundException("Tenant not found"));

        if (tenant.getName().equals(cmd.newName())) {
            throw new IllegalArgumentException("Tenant's old name is equals to new name");
        }

        repository.changeNameById(cmd.tenantId(), cmd.newName());
        tenant.changeName(cmd.newName());
    }
}
