package br.com.barberManagerAPI.application.useCases.barber.delete;

import java.util.UUID;

public record DeleteBarberCommand(UUID barberId) {
}
