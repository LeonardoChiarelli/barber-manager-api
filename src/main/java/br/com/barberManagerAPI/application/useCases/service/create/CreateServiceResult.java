package br.com.barberManagerAPI.application.useCases.service.create;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

public record CreateServiceResult(UUID id, String name, Duration durationMinutes, BigDecimal price, boolean active) {
}
