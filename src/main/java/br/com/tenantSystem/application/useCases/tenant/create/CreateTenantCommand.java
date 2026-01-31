package br.com.tenantSystem.application.useCases.tenant.create;

import java.time.ZoneId;

public record CreateTenantCommand(String name, ZoneId timezone, boolean active) {
}
