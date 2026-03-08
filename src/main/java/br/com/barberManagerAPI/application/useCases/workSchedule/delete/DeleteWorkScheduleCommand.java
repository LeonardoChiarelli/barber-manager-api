package br.com.barberManagerAPI.application.useCases.workSchedule.delete;

import java.util.UUID;

public record DeleteWorkScheduleCommand(UUID barberId, int weekDay) {
}
