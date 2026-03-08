package br.com.barberManagerAPI.domain.barberShop;

import java.time.ZoneId;
import java.util.*;

public class BarberShop {
    private final UUID id;
    private String name;
    private final ZoneId timezone;
    private boolean active;
    private final List<UUID> barberIds = new ArrayList<>();

    private BarberShop(UUID id, String name, ZoneId timezone, List<UUID> barberIds) {
        if (id == null || name.isBlank() || timezone == null) {
            throw new IllegalArgumentException("All BarberShop core must be provided.");
        }

        this.id = id;
        this.name = name;
        this.timezone = timezone;
        this.barberIds.addAll(barberIds);
    }

    public static BarberShopBuilder builder() {
        return new BarberShopBuilder();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ZoneId getTimezone() {
        return timezone;
    }

    public boolean isActive() {
        return active;
    }

    public List<UUID> getBarberIds() { return   barberIds; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BarberShop barberShop = (BarberShop) o;
        return Objects.equals(getId(), barberShop.getId()) && Objects.equals(getName(), barberShop.getName()) && Objects.equals(getTimezone(), barberShop.getTimezone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getTimezone());
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Name: %s
                Timezone: %s
                Active: %s
                Barber IDs: %s
                """, getId(), getName(), getTimezone(), isActive(), getBarberIds());
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public static class BarberShopBuilder {
        private String name;
        private ZoneId timezone;
        private List<UUID> barberIds = new ArrayList<>();

        public BarberShopBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BarberShopBuilder timezone(ZoneId timezone) {
            this.timezone = timezone;
            return this;
        }

        public BarberShopBuilder barberIds(List<UUID> barberIds) {
            this.barberIds = barberIds;
            return this;
        }

        public BarberShop build() {
            if (name.isBlank() || timezone == null) {
                throw new IllegalArgumentException("All BarberShop core must be provided.");
            }
            return new BarberShop(UUID.randomUUID(), name, timezone, barberIds);
        }
    }
}
