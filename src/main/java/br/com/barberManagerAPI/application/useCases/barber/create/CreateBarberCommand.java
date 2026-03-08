package br.com.barberManagerAPI.application.useCases.barber.create;

import java.util.UUID;

public record CreateBarberCommand(String name, UUID tenantId, String nickname, boolean active) {
}
