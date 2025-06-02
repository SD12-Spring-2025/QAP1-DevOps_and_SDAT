package com.fitnesstracker;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Workout} class.
 * Covers positive and negative scenarios for workout creation and calorie calculation.
 */
class WorkoutTest {

    /**
     * Tests that calories are calculated correctly for a valid duration and activity type.
     * Positive scenario.
     */
    @Test
    void testCalculateCaloriesBurned_validDuration() {
        ActivityType running = ActivityType.RUNNING;
        Duration duration = Duration.ofMinutes(60);
        LocalDate date = LocalDate.of(2024, 5, 20);

        Workout workout = new Workout(running, duration, date);
        // Expected calories: 60 minutes * 0.10 calories/minute = 6.0
        assertEquals(6.0, workout.getCaloriesBurned(), 0.001, "Calories should be calculated correctly for valid duration.");
    }

    /**
     * Tests that calories are calculated correctly when duration is zero.
     * Edge case scenario.
     */
    @Test
    void testCalculateCaloriesBurned_zeroDuration() {
        ActivityType cycling = ActivityType.CYCLING;
        Duration duration = Duration.ofMinutes(0); // Zero duration
        LocalDate date = LocalDate.of(2024, 5, 21);

        // Expecting IllegalArgumentException for zero duration as per Workout constructor validation
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Workout(cycling, duration, date), "Should throw IllegalArgumentException for zero duration.");

        assertEquals("Workout duration must be positive.", thrown.getMessage());
    }

    /**
     * Tests that an IllegalArgumentException is thrown when setting a negative duration.
     * Negative scenario.
     */
    @Test
    void testSetDuration_negativeDurationThrowsException() {
        ActivityType weightlifting = ActivityType.WEIGHTLIFTING;
        Duration initialDuration = Duration.ofMinutes(30);
        LocalDate date = LocalDate.of(2024, 5, 22);
        Workout workout = new Workout(weightlifting, initialDuration, date);

        Duration negativeDuration = Duration.ofMinutes(-10);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> workout.setDuration(negativeDuration), "Should throw IllegalArgumentException for negative duration.");

        assertEquals("Workout duration must be positive.", thrown.getMessage());
    }

    /**
     * Tests that an IllegalArgumentException is thrown when creating a workout with null duration.
     * Negative scenario.
     */
    @Test
    void testConstructor_nullDurationThrowsException() {
        ActivityType running = ActivityType.RUNNING;
        LocalDate date = LocalDate.of(2024, 5, 23);

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> new Workout(running, null, date), "Should throw NullPointerException for null duration.");

        assertEquals("Duration cannot be null.", thrown.getMessage());
    }

    /**
     * Tests that an IllegalArgumentException is thrown when creating a workout with null activity type.
     * Negative scenario.
     */
    @Test
    void testConstructor_nullActivityTypeThrowsException() {
        Duration duration = Duration.ofMinutes(30);
        LocalDate date = LocalDate.of(2024, 5, 23);

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> new Workout(null, duration, date), "Should throw NullPointerException for null activity type.");

        assertEquals("Activity type cannot be null.", thrown.getMessage());
    }
}