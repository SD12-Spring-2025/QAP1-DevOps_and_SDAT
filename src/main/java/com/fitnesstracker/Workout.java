package com.fitnesstracker;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Represents a workout session, tracking calories burned.
 */
public class Workout {
    private ActivityType activityType;
    private Duration duration;
    private LocalDate date;
    private double caloriesBurned;

    public Workout(ActivityType activityType, Duration duration, LocalDate date) {
        this.activityType = activityType;
        this.duration = duration;
        this.date = date;
        calculateCaloriesBurned();
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

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    private void calculateCaloriesBurned() {
        caloriesBurned = activityType.getCaloriesPerMinute() * duration.toMinutes();
    }

    @Override
    public String toString() {
        return String.format("Activity: %s, Duration: %d minutes, Date: %s, Calories Burned: %.2f",
                activityType.getName(), duration.toMinutes(), date, caloriesBurned);
    }
}
