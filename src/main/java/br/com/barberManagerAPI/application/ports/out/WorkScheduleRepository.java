package br.com.barberManagerAPI.application.ports.out;

import br.com.barberManagerAPI.domain.barber.WorkSchedule;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public interface WorkScheduleRepository {

    // ----- DDL -----


    // ----- DML -----
    void save(WorkSchedule workSchedule);
    void changeIntervalTime(UUID workScheduleId, LocalTime startTime, LocalTime endTime);
    void deleteById(UUID workScheduleId);

    // ----- DQL -----
    Optional<WorkSchedule> findById(UUID schedulingId);
    Optional<WorkSchedule> findByBarberId(UUID schedulingId);
    Optional<WorkSchedule> findByBarberIdAndWorkDay(UUID schedulingId, int workDay);
    boolean existsByIdAndWeekDay(UUID barberId, int weekDay);

    // ----- DTL -----


    // ----- DCL -----
}
