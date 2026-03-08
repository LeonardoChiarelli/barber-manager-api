package br.com.barberManagerAPI.application.useCases.workSchedule.create;

import java.time.LocalTime;
import java.util.UUID;

public record CreateWorkScheduleCommand(UUID barberId, int weekDay, LocalTime startTime, LocalTime endTime, LocalTime intervalStart, LocalTime intervalEnd) {
}
