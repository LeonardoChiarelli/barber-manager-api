package br.com.tenantSystem.application.useCases.service.deactivate;

import java.util.UUID;

public record DeactivateServiceCommand(UUID serviceId) {
}
