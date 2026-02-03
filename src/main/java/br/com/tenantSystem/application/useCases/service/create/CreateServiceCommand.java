package br.com.tenantSystem.application.useCases.service.create;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

public record CreateServiceCommand(UUID tenantId, String name, Duration durationMinutes, BigDecimal price, boolean active) {
}
