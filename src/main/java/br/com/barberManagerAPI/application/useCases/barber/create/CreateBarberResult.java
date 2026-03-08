package br.com.barberManagerAPI.application.useCases.barber.create;

import java.util.UUID;

public record CreateBarberResult(UUID id, String name, String nickname, boolean active) {
}
