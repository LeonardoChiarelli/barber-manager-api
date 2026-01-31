package br.com.tenantSystem.application.useCases.barber.create;

import java.util.UUID;

public record CreateBarberResult(UUID id, String name, String nickname, boolean active) {
}
