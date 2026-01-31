package br.com.tenantSystem.application.useCases.tenant.activate;

import java.util.UUID;

public record ActivateTenantCommand(UUID tenantId) {
}
