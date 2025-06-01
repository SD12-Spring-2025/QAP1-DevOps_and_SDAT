package com.fitnesstracker;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Represents a basic workout session.
 */
public class Workout {
    private ActivityType activityType;
    private Duration duration;
    private LocalDate date;

    public Workout(ActivityType activityType, Duration duration, LocalDate date) {
        this.activityType = activityType;
        this.duration = duration;
        this.date = date;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalDate getDate() {
        return date;
    }
}