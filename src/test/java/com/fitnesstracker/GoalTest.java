package com.fitnesstracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Goal} class.
 * Covers goal achievement, progress updates, and validation of target values and dates.
 */
class GoalTest {

    /**
     * Tests that a goal is correctly marked as achieved when current value meets target.
     * Positive scenario.
     */
    @Test
    void testIsAchieved_goalMet() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Goal goal = new Goal("Weekly Minutes", 300, startDate, endDate);
        goal.updateProgress(300); // Meet the target

        assertTrue(goal.isAchieved(), "Goal should be achieved when current value equals target.");
    }

    /**
     * Tests that a goal is correctly marked as achieved when current value exceeds target.
     * Positive scenario.
     */
    @Test
    void testIsAchieved_goalExceeded() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Goal goal = new Goal("Workouts Completed", 10, startDate, endDate);
        goal.updateProgress(12); // Exceed the target

        assertTrue(goal.isAchieved(), "Goal should be achieved when current value exceeds target.");
    }

    /**
     * Tests that a goal is correctly marked as not achieved when current value is below target.
     * Negative scenario.
     */
    @Test
    void testIsAchieved_goalNotMet() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Goal goal = new Goal("Daily Calories Burned", 500, startDate, endDate);
        goal.updateProgress(450); // Below the target

        assertFalse(goal.isAchieved(), "Goal should not be achieved when current value is below target.");
    }

    /**
     * Tests that updateProgress method correctly adds to the current value.
     * Positive scenario.
     */
    @Test
    void testUpdateProgress_addsCorrectly() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Goal goal = new Goal("Total Distance (km)", 100, startDate, endDate);
        goal.updateProgress(25);
        goal.updateProgress(15);

        assertEquals(40, goal.getCurrentValue(), 0.001, "Progress should be updated correctly.");
    }

    /**
     * Tests that an IllegalArgumentException is thrown when target value is zero.
     * Negative scenario.
     */
    @Test
    void testConstructor_zeroTargetValueThrowsException() {
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 30);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Goal("Steps", 0, startDate, endDate), "Should throw IllegalArgumentException for zero target value.");

        assertEquals("Target value must be positive.", thrown.getMessage());
    }

    /**
     * Tests that an IllegalArgumentException is thrown when target value is negative.
     * Negative scenario.
     */
    @Test
    void testConstructor_negativeTargetValueThrowsException() {
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 30);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Goal("Steps", -100, startDate, endDate), "Should throw IllegalArgumentException for negative target value.");

        assertEquals("Target value must be positive.", thrown.getMessage());
    }

    /**
     * Tests that an IllegalArgumentException is thrown when start date is after end date.
     * Negative scenario.
     */
    @Test
    void testConstructor_startDateAfterEndDateThrowsException() {
        LocalDate startDate = LocalDate.of(2024, 7, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 30);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Goal("Test Goal", 100, startDate, endDate), "Should throw IllegalArgumentException if start date is after end date.");

        assertEquals("Start date cannot be after end date.", thrown.getMessage());
    }
}