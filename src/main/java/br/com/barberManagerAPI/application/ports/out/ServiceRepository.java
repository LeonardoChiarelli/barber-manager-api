package br.com.barberManagerAPI.application.ports.out;

import br.com.barberManagerAPI.domain.service.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceRepository {

    // ----- DDL -----

    // ----- DML -----
    void save(Service service);
    void deleteById(UUID id);
    void activateById(UUID id);
    void deactivateById(UUID id);
    void changePriceById(UUID id, BigDecimal price);
    void changeDurationById(UUID id, Duration duration);
    void changeNameById(UUID id, String newName);

    // ----- DQL -----
    List<Service> findAll();
    List<Service> findAllActive();
    Optional<List<Service>> findAllByPrice(BigDecimal price);
    Optional<Service> findById(UUID id);
    boolean existsByNameAndTenantId(String name, UUID tenantId);

    // ----- DTL -----

    // ----- DCL -----
}
