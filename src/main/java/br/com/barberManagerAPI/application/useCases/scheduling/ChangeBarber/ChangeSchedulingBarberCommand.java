package br.com.barberManagerAPI.application.useCases.scheduling.ChangeBarber;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChangeSchedulingBarberCommand(UUID schedulingId, UUID oldBarberId, UUID newBarberId, LocalDateTime startTime) {
}
