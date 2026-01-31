package br.com.tenantSystem.application.ports.out;

import br.com.tenantSystem.domain.tenant.Tenant;

import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

public interface TenantRepository {

    // ----- DDL -----

    // ----- DML -----
    void save(Tenant tenant);
    void deleteById(UUID tenantId);
    void deactivateById(UUID tenantId);
    void activateById(UUID tenantId);
    void changeNameById(UUID tenantId, String newName);

    // ----- DQL -----
    Optional<Tenant> findById(UUID tenantId);
    boolean existsByNameAndTimezone(String name, ZoneId timezone);
    boolean existsById(UUID tenantId);

    // ----- DTL -----
    // ----- DCL -----
}
