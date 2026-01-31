package br.com.tenantSystem.domain.barber;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class WorkSchedule {
    private final UUID id;
    private final UUID barberId;
    private final int weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime interval;

    private WorkSchedule(UUID id, UUID barberId, int weekDay, LocalTime startTime, LocalTime endTime, LocalTime interval) {
        if (id == null || barberId == null || startTime == null || endTime == null || interval == null) {
            throw new IllegalArgumentException("All Service core must be provided.");
        }

        this.id = id;
        this.barberId = barberId;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.interval = interval;
    }

    public static WorkScheduleBuilder builder() {
        return new WorkScheduleBuilder();
    }

    public UUID getId() {
        return id;
    }

    public UUID getBarberId() {
        return barberId;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getInterval() {
        return interval;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WorkSchedule that = (WorkSchedule) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getBarberId(), that.getBarberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBarberId());
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Barber ID: %s
                Weekdays: %d
                StartTime: %s
                EndTime: %s
                Interval: %s
                """, getId(), getBarberId(), getWeekDay(), getStartTime(), getEndTime(), getInterval());
    }

    public void changeStartTime(LocalTime newStartTime) {
        this.startTime = newStartTime;
    }

    public void changeEndTime(LocalTime newEndTime) {
        this.endTime = newEndTime;
    }

    public void changeInterval(LocalTime newInterval) {
        this.interval = newInterval;
    }

    public static class WorkScheduleBuilder {
        private UUID barberId;
        private int weekDay;
        private LocalTime startTime;
        private LocalTime endTime;
        private LocalTime interval;

        public WorkScheduleBuilder barberId(UUID barberId) {
            this.barberId = barberId;
            return this;
        }

        public WorkScheduleBuilder weekDay(int weekDay) {
            this.weekDay = weekDay;
            return this;
        }

        public WorkScheduleBuilder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public WorkScheduleBuilder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public WorkScheduleBuilder interval(LocalTime interval) {
            this.interval = interval;
            return this;
        }

        public WorkSchedule build() {
            return new WorkSchedule(UUID.randomUUID(), barberId, weekDay, startTime, endTime, interval);
        }
    }
}
