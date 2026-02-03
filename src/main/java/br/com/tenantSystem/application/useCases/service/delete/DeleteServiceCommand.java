package br.com.tenantSystem.application.useCases.service.delete;

import java.util.UUID;

public record DeleteServiceCommand(UUID serviceId) {
}
