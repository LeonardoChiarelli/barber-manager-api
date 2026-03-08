package br.com.barberManagerAPI.domain.barber;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class WorkSchedule {
    private final UUID id;
    private final UUID barberId;
    private final int weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime intervalStart;
    private LocalTime intervalEnd;

    private WorkSchedule(UUID id, UUID barberId, int weekDay, LocalTime startTime, LocalTime endTime, LocalTime intervalStart, LocalTime intervalEnd) {
        if (id == null || barberId == null || startTime == null || endTime == null || intervalStart == null || intervalEnd == null) {
            throw new IllegalArgumentException("All Service core must be provided.");
        }

        this.id = id;
        this.barberId = barberId;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
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

    public LocalTime getIntervalStart() {
        return intervalStart;
    }

    public LocalTime getIntervalEnd() {
        return intervalEnd;
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
                Interval Start: %s
                Interval End: %s
                """, getId(), getBarberId(), getWeekDay(), getStartTime(), getEndTime(), getIntervalStart(), getIntervalEnd());
    }

    public void changeStartTime(LocalTime newStartTime) {
        this.startTime = newStartTime;
    }

    public void changeEndTime(LocalTime newEndTime) {
        this.endTime = newEndTime;
    }

    public void changeIntervalStart(LocalTime newIntervalStart) {
        this.intervalStart = newIntervalStart;
    }

    public void changeIntervalEnd(LocalTime newIntervalEnd) {
        this.intervalEnd = newIntervalEnd;
    }

    public static class WorkScheduleBuilder {
        private UUID barberId;
        private int weekDay;
        private LocalTime startTime;
        private LocalTime endTime;
        private LocalTime intervalStart;
        private LocalTime intervalEnd;


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

        public WorkScheduleBuilder intervalStart(LocalTime intervalStart) {
            this.intervalStart = intervalStart;
            return this;
        }

        public WorkScheduleBuilder intervalEnd(LocalTime intervalEnd) {
            this.intervalEnd = intervalEnd;
            return this;
        }

        public WorkSchedule build() {
            return new WorkSchedule(UUID.randomUUID(), barberId, weekDay, startTime, endTime, intervalStart, intervalEnd);
        }
    }
}
