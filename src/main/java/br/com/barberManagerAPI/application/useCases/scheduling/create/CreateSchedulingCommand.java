package br.com.barberManagerAPI.application.useCases.scheduling.create;

import br.com.barberManagerAPI.domain.scheduling.SchedulingOrigin;
import br.com.barberManagerAPI.domain.scheduling.SchedulingStatus;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record CreateSchedulingCommand(UUID tenantId, UUID barberId, UUID serviceId, String clientName, String clientPhone, LocalDateTime startTime, SchedulingStatus schedulingStatus, SchedulingOrigin schedulingOrigin, Optional<String> googleEventId) {
}
