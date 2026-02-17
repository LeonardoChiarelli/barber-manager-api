package br.com.tenantSystem.application.useCases.workSchedule.delete;

import java.util.UUID;

public record DeleteWorkScheduleCommand(UUID barberId, int weekDay) {
}
