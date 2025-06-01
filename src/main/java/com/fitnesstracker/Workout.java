package com.fitnesstracker;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Single workout session.
 * activity type, duration, date, and calories burned.
 */
public class Workout {
    private ActivityType activityType;
    private Duration duration;
    private LocalDate date;
    private double caloriesBurned;

    /**
     * Constructs a new Workout instance.
     *
     * @param activityType The type of activity performed.
     * @param duration The duration of the workout. Must be a positive duration.
     * @param date The date the workout was performed.
     * @throws IllegalArgumentException if the duration is null or not positive.
     */
    public Workout(ActivityType activityType, Duration duration, LocalDate date) {
        this.activityType = Objects.requireNonNull(activityType, "Activity type cannot be null.");
        setDate(Objects.requireNonNull(date, "Date cannot be null."));
        setDuration(duration); // This will perform validation
        calculateCaloriesBurned();
    }

    /**
     * Returns the type of activity for this workout.
     *
     * @return The activity type.
     */
    public ActivityType getActivityType() {
        return activityType;
    }

    /**
     * Sets the activity type for this workout.
     *
     * @param activityType The new activity type.
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = Objects.requireNonNull(activityType, "Activity type cannot be null.");
        calculateCaloriesBurned(); // Recalculate if activity type changes
    }

    /**
     * Returns the duration of the workout.
     *
     * @return The duration.
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the workout.
     *
     * @param duration The new duration. Must be a positive duration.
     * @throws IllegalArgumentException if the duration is null or not positive.
     */
    public void setDuration(Duration duration) {
        Objects.requireNonNull(duration, "Duration cannot be null.");
        if (duration.isNegative() || duration.isZero()) {
            throw new IllegalArgumentException("Workout duration must be positive.");
        }
        this.duration = duration;
        calculateCaloriesBurned(); // Recalculate if duration changes
    }

    /**
     * Returns the date the workout was performed.
     *
     * @return The workout date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the workout.
     *
     * @param date The new workout date.
     */
    public void setDate(LocalDate date) {
        this.date = Objects.requireNonNull(date, "Date cannot be null.");
    }

    /**
     * Returns the estimated calories burned during this workout.
     *
     * @return The calories burned.
     */
    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    /**
     * Calculates and sets the estimated calories burned based on activity type and duration.
     * This method is called internally when activity type or duration changes.
     */
    private void calculateCaloriesBurned() {
        if (activityType!= null && duration!= null) {
            this.caloriesBurned = activityType.getCaloriesPerMinute() * duration.toMinutes();
        } else {
            this.caloriesBurned = 0.0;
        }
    }

    @Override
    public String toString() {
        return String.format("Activity: %s, Duration: %d minutes, Date: %s, Calories Burned: %.2f",
                activityType.getName(), duration.toMinutes(), date, caloriesBurned);
    }
}