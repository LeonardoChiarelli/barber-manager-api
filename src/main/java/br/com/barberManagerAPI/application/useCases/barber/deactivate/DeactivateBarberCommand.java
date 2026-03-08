package br.com.barberManagerAPI.application.useCases.barber.deactivate;

import java.util.UUID;

public record DeactivateBarberCommand(UUID barberId) {
}
