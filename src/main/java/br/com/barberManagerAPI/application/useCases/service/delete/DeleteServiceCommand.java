package br.com.barberManagerAPI.application.useCases.service.delete;

import java.util.UUID;

public record DeleteServiceCommand(UUID serviceId) {
}
