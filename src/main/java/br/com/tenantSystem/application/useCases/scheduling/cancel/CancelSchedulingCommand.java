package br.com.tenantSystem.application.useCases.scheduling.cancel;

import java.util.UUID;

public record CancelSchedulingCommand(UUID schedulingId) {
}
