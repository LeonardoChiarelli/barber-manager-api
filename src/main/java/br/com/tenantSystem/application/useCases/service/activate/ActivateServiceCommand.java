package br.com.tenantSystem.application.useCases.service.activate;

import java.util.UUID;

public record ActivateServiceCommand(UUID serviceId) {
}
