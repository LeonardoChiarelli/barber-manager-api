package br.com.tenantSystem.application.ports.out;

import br.com.tenantSystem.domain.barber.WorkSchedule;

import java.time.LocalDateTime;
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
