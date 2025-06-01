package com.fitnesstracker;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a fitness goal set by a user.
 * Tracks the goal title, target value, current progress, and achievement status.
 */
public class Goal {
    private String goalTitle;
    private double targetValue;
    private double currentValue;
    private LocalDate startDate;
    private LocalDate endDate;

    public Goal(String goalTitle, double targetValue, LocalDate startDate, LocalDate endDate) {
        this.goalTitle = Objects.requireNonNull(goalTitle, "Goal title cannot be null.");
        setTargetValue(targetValue);
        this.currentValue = 0.0; // Goals start with 0 progress
        setStartDate(Objects.requireNonNull(startDate, "Start date cannot be null."));
        setEndDate(Objects.requireNonNull(endDate, "End date cannot be null."));

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public void setGoalTitle(String goalTitle) {
        this.goalTitle = Objects.requireNonNull(goalTitle, "Goal title cannot be null.");
    }

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        if (targetValue <= 0) {
            throw new IllegalArgumentException("Target value must be positive.");
        }
        this.targetValue = targetValue;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = Objects.requireNonNull(startDate, "Start date cannot be null.");
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = Objects.requireNonNull(endDate, "End date cannot be null.");
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void updateProgress(double value) {
        this.currentValue += value;
    }

    public boolean isAchieved() {
        return currentValue >= targetValue;
    }

    @Override
    public String toString() {
        return String.format("Goal Title: %s, Target: %.2f, Current: %.2f, Achieved: %b, Period: %s to %s",
                goalTitle, targetValue, currentValue, isAchieved(), startDate, endDate);
    }
}