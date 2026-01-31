package br.com.tenantSystem.domain.scheduling;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class Scheduling {
    private final UUID id;
    private final UUID tenantId;
    private UUID barberId;
    private final UUID serviceId;
    private final String clientName;
    private final String clientPhone;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SchedulingStatus schedulingStatus;
    private final SchedulingOrigin schedulingOrigin;
    private final String googleEventId;
    private final LocalTime createdAt;

    private Scheduling(UUID id,
                       UUID tenantId,
                       UUID barberId,
                       UUID serviceId,
                       String clientName,
                       String clientPhone,
                       LocalDateTime startTime,
                       LocalDateTime endTime,
                       SchedulingStatus schedulingStatus,
                       SchedulingOrigin schedulingOrigin,
                       String googleEventId,
                       LocalTime createdAt) {
        if (id == null || tenantId == null || barberId == null || serviceId == null || clientName == null || clientPhone == null || startTime == null || endTime == null || schedulingStatus == null || schedulingOrigin == null || googleEventId == null || createdAt == null) {
            throw new IllegalArgumentException("All scheduling core must be provided.");
        }

        this.id = id;
        this.tenantId = tenantId;
        this.barberId = barberId;
        this.serviceId = serviceId;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.startTime = startTime;
        this.endTime = endTime;
        this.schedulingStatus = schedulingStatus;
        this.schedulingOrigin = schedulingOrigin;
        this.googleEventId = googleEventId;
        this.createdAt = createdAt;
    }

    public static SchedulingBuilder builder() {
        return new SchedulingBuilder();
    }

    public UUID getId() {
        return id;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public UUID getBarberId() {
        return barberId;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public SchedulingStatus getStatus() {
        return schedulingStatus;
    }

    public SchedulingOrigin getOrigin() {
        return schedulingOrigin;
    }

    public String getGoogleEventId() {
        return googleEventId;
    }

    public LocalTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Scheduling that = (Scheduling) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTenantId(), that.getTenantId()) && Objects.equals(getBarberId(), that.getBarberId()) && Objects.equals(getServiceId(), that.getServiceId()) && Objects.equals(getClientName(), that.getClientName()) && Objects.equals(getClientPhone(), that.getClientPhone()) && Objects.equals(getStartTime(), that.getStartTime()) && Objects.equals(getEndTime(), that.getEndTime()) && getStatus() == that.getStatus() && getOrigin() == that.getOrigin() && Objects.equals(getGoogleEventId(), that.getGoogleEventId()) && Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTenantId(), getBarberId(), getServiceId(), getClientName(), getClientPhone(), getStartTime(), getEndTime(), getStatus(), getOrigin(), getGoogleEventId(), getCreatedAt());
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Tenant ID: %s
                Barber ID: %s
                Service ID: %s
                Client Name: %s
                Client Phone: %s
                Start Time: %s
                End Time: %s
                Status: %s
                Origin: %s
                Google Event ID: %s
                Created At: %s
                """, getId(), getTenantId(), getBarberId(), getServiceId(), getClientName(), getClientPhone(), getStartTime(), getEndTime(), getStatus(), getOrigin(), getGoogleEventId(), getCreatedAt());
    }

    public void changeBarberId(UUID barberId) {
        this.barberId = barberId;
    }

    public void changeStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void changeEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void changeStatus(SchedulingStatus schedulingStatus) {
        this.schedulingStatus = schedulingStatus;
    }

    public static class SchedulingBuilder {
        private UUID tenantId;
        private UUID barberId;
        private UUID serviceId;
        private String clientName;
        private String clientPhone;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private SchedulingStatus schedulingStatus;
        private SchedulingOrigin schedulingOrigin;
        private String googleEventId;

        public SchedulingBuilder tenantId(UUID tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public SchedulingBuilder barberId(UUID barberId) {
            this.barberId = barberId;
            return this;
        }

        public SchedulingBuilder serviceId(UUID serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public SchedulingBuilder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public SchedulingBuilder clientPhone(String clientPhone) {
            this.clientPhone = clientPhone;
            return this;
        }

        public SchedulingBuilder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public SchedulingBuilder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public SchedulingBuilder schedulingStatus(SchedulingStatus schedulingStatus) {
            this.schedulingStatus = schedulingStatus;
            return this;
        }

        public SchedulingBuilder schedulingOrigin(SchedulingOrigin schedulingOrigin) {
            this.schedulingOrigin = schedulingOrigin;
            return this;
        }

        public SchedulingBuilder googleEventId(String googleEventId) {
            this.googleEventId = googleEventId;
            return this;
        }

        public Scheduling build() {
            return new Scheduling(UUID.randomUUID(), tenantId, barberId, serviceId, clientName, clientPhone, startTime, endTime, schedulingStatus, schedulingOrigin, googleEventId, LocalTime.now());
        }
    }
}
