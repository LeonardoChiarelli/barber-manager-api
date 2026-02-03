package br.com.tenantSystem.domain.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

public class Service {
    private final UUID id;
    private final UUID tenantId;
    private String name;
    private Duration durationMinutes;
    private BigDecimal price;
    private boolean active;

    private Service(UUID id, UUID tenantId, String name, Duration durationMinutes, BigDecimal price, boolean active) {
        if (id == null || tenantId == null || name.isBlank() || durationMinutes.isNegative() || durationMinutes.isZero() || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("All Tenant core must be provided.");
        }

        this.id = id;
        this.tenantId = tenantId;
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.price = price;
        this.active = active;
    }

    public static ServiceBuilder builder() {
        return new ServiceBuilder();
    }

    public UUID getId() {
        return id;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public String getName() {
        return name;
    }

    public Duration getDurationMinutes() {
        return durationMinutes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(getId(), service.getId()) && Objects.equals(getTenantId(), service.getTenantId()) && Objects.equals(getName(), service.getName()) && Objects.equals(getDurationMinutes(), service.getDurationMinutes()) && Objects.equals(getPrice(), service.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTenantId(), getName(), getDurationMinutes(), getPrice());
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Tenant ID: %s
                Name: %s
                Duration: %s
                Price: %.2f
                Active: %s
                """, getId(), getTenantId(), getName(), getDurationMinutes(), getPrice(), isActive());
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDurationMinutes(Duration newDurationMinutes) {
        this.durationMinutes = newDurationMinutes;
    }

    public void changePrice(BigDecimal newPrice) {
        this.price = newPrice;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public static class ServiceBuilder {
        private UUID tenantId;
        private String name;
        private Duration durationMinutes;
        private BigDecimal price;
        private boolean active;

        public ServiceBuilder tenantId(UUID tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public ServiceBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ServiceBuilder durationMinutes(Duration durationMinutes) {
            this.durationMinutes = durationMinutes;
            return this;
        }

        public ServiceBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ServiceBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public Service build() {
            return new Service(UUID.randomUUID(), tenantId, name, durationMinutes, price, active);
        }
    }
}
