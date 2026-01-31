package br.com.tenantSystem.application.useCases.tenant.create;

import java.time.ZoneId;
import java.util.UUID;

public record CreateTenantResult(UUID id, String name, ZoneId timezone, boolean active) {
}
