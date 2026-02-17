package br.com.tenantSystem.application.useCases.scheduling.confirm;

import java.util.UUID;

public record ConfirmSchedulingCommand(UUID schedulingId) {
}
