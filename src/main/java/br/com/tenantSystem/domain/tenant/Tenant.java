package br.com.tenantSystem.domain.tenant;

import java.time.ZoneId;
import java.util.Objects;
import java.util.UUID;

public class Tenant {
    private final UUID id;
    private String name;
    private final ZoneId timezone;
    private boolean active;

    private Tenant(UUID id, String name, ZoneId timezone) {
        if (id == null || name.isBlank() || timezone == null) {
            throw new IllegalArgumentException("All Tenant core must be provided.");
        }

        this.id = id;
        this.name = name;
        this.timezone = timezone;
    }

    public static TenantBuilder builder() {
        return new TenantBuilder();
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(getId(), tenant.getId()) && Objects.equals(getName(), tenant.getName()) && Objects.equals(getTimezone(), tenant.getTimezone());
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
                """, getId(), getName(), getTimezone(), isActive());
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

    public static class TenantBuilder {
        private String name;
        private ZoneId timezone;

        public TenantBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TenantBuilder timezone(ZoneId timezone) {
            this.timezone = timezone;
            return this;
        }

        public Tenant build() {
            if (name.isBlank() || timezone == null) {
                throw new IllegalArgumentException("All Tenant core must be provided.");
            }
            return new Tenant(UUID.randomUUID(), name, timezone);
        }
    }
}
