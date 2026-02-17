package br.com.tenantSystem.application.useCases.scheduling.create;

import br.com.tenantSystem.domain.scheduling.SchedulingOrigin;
import br.com.tenantSystem.domain.scheduling.SchedulingStatus;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record CreateSchedulingCommand(UUID tenantId, UUID barberId, UUID serviceId, String clientName, String clientPhone, LocalDateTime startTime, SchedulingStatus schedulingStatus, SchedulingOrigin schedulingOrigin, Optional<String> googleEventId) {
}
