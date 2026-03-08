package br.com.barberManagerAPI.application.useCases.service.changeName;

import java.util.UUID;

public record ChangeServiceNameCommand(UUID serviceId, String newName) {
}
