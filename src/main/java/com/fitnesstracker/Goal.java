package com.fitnesstracker;

import java.time.LocalDate;

/**
 * Represents a fitness goal set by a user.
 * Tracks goal title, target value, progress, and time period.
 */
public class Goal {
    private String goalTitle;
    private double targetValue;
    private double currentValue;
    private LocalDate startDate;
    private LocalDate endDate;

    public Goal(String goalTitle, double targetValue, LocalDate startDate, LocalDate endDate) {
        this.goalTitle = goalTitle;
        this.targetValue = targetValue;
        this.currentValue = 0.0;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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
