package br.com.tenantSystem.application.useCases.tenant.deactivate;

import java.util.UUID;

public record DeactivateTenantCommand(UUID tenantId) {
}
