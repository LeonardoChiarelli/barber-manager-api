package br.com.barberManagerAPI.application.useCases.scheduling.confirm;

import java.util.UUID;

public record ConfirmSchedulingCommand(UUID schedulingId) {
}
