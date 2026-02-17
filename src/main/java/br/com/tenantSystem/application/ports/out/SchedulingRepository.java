package br.com.tenantSystem.application.ports.out;

import br.com.tenantSystem.domain.scheduling.Scheduling;
import br.com.tenantSystem.domain.scheduling.SchedulingOrigin;
import br.com.tenantSystem.domain.scheduling.SchedulingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SchedulingRepository {

    // ----- DDL -----

    // ----- DML -----
    void save(Scheduling scheduling);
    void changeBarberById(UUID barberId, UUID schedulingId);
    void changeStartTime(UUID schedulingId, LocalDateTime startTime);
    void changeEndTime(UUID schedulingId, LocalDateTime endTime);
    void changeStatus(UUID schedulingId, SchedulingStatus schedulingStatus);


    // ----- DQL -----
    List<Scheduling> findAll();
    Optional<Scheduling> findById(UUID schedulingId);
    Optional<List<Scheduling>> findAllByBarbeId(UUID barberId);
    Optional<List<Scheduling>> findAllByTenantId(UUID tenantId);
    Optional<List<Scheduling>> findAllByServiceId(UUID serviceId);
    Optional<List<Scheduling>> findAllByClientName(String clientName);
    Optional<List<Scheduling>> findAllByOrigin(SchedulingOrigin schedulingOrigin);
    Optional<List<Scheduling>> findAllByStatus(SchedulingStatus schedulingStatus);
    boolean existsByBarberIdAndStartTimeAndEndTime(UUID barberId, LocalDateTime startTime, LocalDateTime endTime);
    boolean existsById(UUID schedulingId);
    boolean existsByBarberIdAndStartDate(UUID barberId, LocalDateTime startTime);
    boolean existsByBarberIdAndEndTime(UUID schedulingId, LocalDateTime endTime);
    boolean existsByBarberIdAndStartTimeBetween(UUID barberId, LocalDateTime startTime, LocalDateTime endTime);



    // ----- DTL -----

    // ----- DCL -----
}
