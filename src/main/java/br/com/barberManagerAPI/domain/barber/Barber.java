package br.com.barberManagerAPI.domain.barber;

import java.util.Objects;
import java.util.UUID;

public class Barber {
    private final UUID id;
    private final UUID barberShopId;
    private final String name;
    private String nickname;
    private boolean active;

    private Barber(UUID id, UUID barberShopId, String name, boolean active, String nickname) {
        if (id == null || barberShopId == null || name.isBlank() || !active) {
            throw new IllegalArgumentException("All BarberShop core must be provided.");
        }

        this.id = id;
        this.barberShopId = barberShopId;
        this.name = name;
        this.active = active;
        this.nickname = nickname;
    }

    public static BarberBuilder builder() {
        return new BarberBuilder();
    }

    public UUID getId() {
        return id;
    }

    public UUID getBarberShopId() {
        return barberShopId;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Barber barber = (Barber) o;
        return Objects.equals(getId(), barber.getId()) && Objects.equals(getBarberShopId(), barber.getBarberShopId()) && Objects.equals(getName(), barber.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBarberShopId(), getName());
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                BarberShop ID: %s
                Name: %s
                Nickname: %s
                Active: %s
                """, getId(), getBarberShopId(), getName(), getNickname(), isActive());
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public static class BarberBuilder {
        private UUID tenantId;
        private String name;
        private boolean active;
        private String nickname;

        public BarberBuilder tenantId(UUID tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public BarberBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BarberBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public BarberBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Barber build() {
            if (tenantId == null || name == null) {
                throw new IllegalArgumentException("All Barber core must be provided.");
            }

            return new Barber(UUID.randomUUID(), tenantId, name, active, nickname);
        }
    }
}
