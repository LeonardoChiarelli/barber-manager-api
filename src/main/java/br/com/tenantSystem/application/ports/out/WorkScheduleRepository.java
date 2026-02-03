package br.com.tenantSystem.application.ports.out;

import br.com.tenantSystem.domain.barber.WorkSchedule;

import java.util.Optional;
import java.util.UUID;

public interface WorkScheduleRepository {

    // ----- DDL -----


    // ----- DML -----


    // ----- DQL -----
    Optional<WorkSchedule> findById(UUID schedulingId);
    Optional<WorkSchedule> findByBarberId(UUID schedulingId);

    // ----- DTL -----


    // ----- DCL -----
}
