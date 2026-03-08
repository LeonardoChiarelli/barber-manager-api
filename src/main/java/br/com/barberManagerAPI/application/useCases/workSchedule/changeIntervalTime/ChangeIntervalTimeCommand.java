package br.com.barberManagerAPI.application.useCases.workSchedule.changeIntervalTime;

import java.time.LocalTime;
import java.util.UUID;

public record ChangeIntervalTimeCommand(UUID barberId, int weekDay, LocalTime newStartTime, LocalTime newEndTime) {
}
