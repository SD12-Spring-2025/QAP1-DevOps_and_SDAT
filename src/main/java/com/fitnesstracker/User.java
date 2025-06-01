package com.fitnesstracker;

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
}
