package br.com.tenantSystem.application.useCases.barber.deactivate;

import java.util.UUID;

public record DeactivateBarberCommand(UUID barberId) {
}
