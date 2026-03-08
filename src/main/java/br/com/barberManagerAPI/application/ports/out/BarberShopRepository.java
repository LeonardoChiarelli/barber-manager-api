package br.com.barberManagerAPI.application.ports.out;

import br.com.barberManagerAPI.domain.barberShop.BarberShop;

import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

public interface BarberShopRepository {

    // ----- DDL -----

    // ----- DML -----
    void save(BarberShop barberShop);
    void deleteById(UUID tenantId);
    void deactivateById(UUID tenantId);
    void activateById(UUID tenantId);
    void changeNameById(UUID tenantId, String newName);

    // ----- DQL -----
    Optional<BarberShop> findById(UUID tenantId);
    boolean existsByNameAndTimezone(String name, ZoneId timezone);
    boolean existsById(UUID tenantId);

    // ----- DTL -----
    // ----- DCL -----
}
