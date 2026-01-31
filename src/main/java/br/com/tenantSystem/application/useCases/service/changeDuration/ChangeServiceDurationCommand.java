package br.com.tenantSystem.application.useCases.service.changeDuration;

import java.time.Duration;
import java.util.UUID;

public record ChangeServiceDurationCommand(UUID serviceId, Duration newDuration) {
}
