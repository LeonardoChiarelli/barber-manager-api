package br.com.barberManagerAPI.application.useCases.scheduling.changeDateTime;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChangeStartTimeCommand(UUID schedulingId, LocalDateTime newStartTime) {
}
