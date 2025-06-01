package com.fitnesstracker;

import java.time.LocalDate;

/**
 * Represents a basic fitness goal.
 */
public class Goal {
    private String goalTitle;
    private double targetValue;

    public Goal(String goalTitle, double targetValue) {
        this.goalTitle = goalTitle;
        this.targetValue = targetValue;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public double getTargetValue() {
        return targetValue;
    }
}