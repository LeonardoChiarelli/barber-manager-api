package br.com.barberManagerAPI.application.useCases.scheduling.changeService;

import java.util.UUID;

public record ChangeServiceSchedulingCommand(UUID schedulingId, UUID serviceId) {
}
