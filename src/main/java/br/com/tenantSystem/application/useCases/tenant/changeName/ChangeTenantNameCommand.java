package br.com.tenantSystem.application.useCases.tenant.changeName;

import java.util.UUID;

public record ChangeTenantNameCommand(UUID tenantId, String newName) {
}
