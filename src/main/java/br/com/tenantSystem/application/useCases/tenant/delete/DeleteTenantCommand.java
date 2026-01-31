package br.com.tenantSystem.application.useCases.tenant.delete;

import java.util.UUID;

public record DeleteTenantCommand(UUID tenantId) {
}
