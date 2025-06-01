package com.fitnesstracker;

/**
 * Represents different types of fitness activities with their associated
 * calorie burn rates per minute.
 */
public enum ActivityType {
    RUNNING("Running", 10.0),
    WEIGHTLIFTING("Weightlifting", 7.0),
    CYCLING("Cycling", 8.0);

    private final String name;
    private final double caloriesPerMinute;

    ActivityType(String name, double caloriesPerMinute) {
        this.name = name;
        this.caloriesPerMinute = caloriesPerMinute;
    }

    public String getName() {
        return name;
    }

    public double getCaloriesPerMinute() {
        return caloriesPerMinute;
    }

    /**
     * Finds an ActivityType by name (case-insensitive).
     *
     * @param name The name of the activity.
     * @return The matching ActivityType, or throws an exception if invalid.
     */
    public static ActivityType fromName(String name) {
        for (ActivityType type : ActivityType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid activity type: " + name +
                ". Choose from RUNNING, WEIGHTLIFTING, CYCLING.");
    }
}
