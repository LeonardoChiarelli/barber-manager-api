package br.com.tenantSystem.application.useCases.barber.delete;

import java.util.UUID;

public record DeleteBarberCommand(UUID barberId) {
}
