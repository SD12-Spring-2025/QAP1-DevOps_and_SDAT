package com.fitnesstracker;

import java.time.LocalDate; // 🔹 Import added!
import java.util.ArrayList;
import java.util.List;

/**
 * User with workouts and fitness goals.
 */
public class User {
    private final String name;
    private final String userId;
    private final List<Workout> workouts;
    private final List<Goal> goals;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.workouts = new ArrayList<>();
        this.goals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    public List<Goal> getGoals() {
        return goals;
    }

    /**
     * Returns only active goals.
     */
    public List<Goal> getActiveGoals() {
        return goals.stream()
                .filter(g -> LocalDate.now().isBefore(g.getEndDate()))
                .toList();
    }

    /**
     * Calculates total minutes spent on a specific activity type.
     */
    public long getTotalWorkoutMinutes(ActivityType activityType) {
        return workouts.stream()
                .filter(w -> w.getActivityType() == activityType)
                .mapToLong(w -> w.getDuration().toMinutes())
                .sum();
    }

    /**
     * Calculates total calories burned for a specific activity type.
     */
    public double getTotalCaloriesBurned(ActivityType activityType) {
        return workouts.stream()
                .filter(w -> w.getActivityType() == activityType)
                .mapToDouble(Workout::getCaloriesBurned)
                .sum();
    }
}