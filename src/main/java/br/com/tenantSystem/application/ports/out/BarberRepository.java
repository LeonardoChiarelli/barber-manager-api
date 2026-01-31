package br.com.tenantSystem.application.ports.out;

import br.com.tenantSystem.domain.barber.Barber;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BarberRepository {
    // ----- DDL -----

    // ----- DML -----
    void save(Barber barber);
    void activateById(UUID barberId);
    void deactivateById(UUID barberId);
    void deleteById(UUID barberId);
    void changeNicknameById(UUID barberId, String nickname);

    // ----- DQL -----
    List<Barber> findAll();
    List<Barber> findAllActive();
    Optional<Barber> findById(UUID barberId);
    Optional<Barber> findByName(String name);
    Optional<Barber> findByNickname(String nickname);
    boolean existsByBarberNameAndNickname(String name, String nickname);

    // ----- DTL -----

    // ----- DCL -----
}
