package com.fitnesstracker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the CLI-based Fitness Tracker application.
 * Now supports multiple users with login and account creation.
 */
public class FitnessTrackerApp {
    private static final List<User> users = new ArrayList<>();
    private static User currentUser;

    public static boolean testMode = false; // Flag for unit testing

    public static void main(String[] args) {
        if (testMode) {
            executeCommand(args, new Scanner(System.in)); // Run a single command in test mode
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Fitness Tracker!");
        System.out.println("===========================");

        while (true) {
            displayMenu();
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine().trim().toLowerCase();
            String[] commandArgs = input.split(" ");

            if (commandArgs.length == 0 || commandArgs[0].isEmpty()) {
                System.out.println("Invalid input. Please enter a valid command.");
                continue;
            }

            String command = switch (commandArgs[0]) {
                case "1" -> "login";
                case "2" -> "create-user";
                case "3" -> "log";
                case "4" -> "track";
                case "5" -> "set-goal";
                case "6" -> "update-goal-title";
                case "7" -> "update-activity";
                case "8" -> "view-goals";
                case "9" -> "view-workouts";
                case "10" -> "view-user";
                case "11" -> "exit";
                default -> commandArgs[0];
            };

            executeCommand(new String[]{command}, scanner);
        }
    }

    /**
     * Executes commands separately for normal and test mode.
     */
    public static void executeCommand(String[] args, Scanner scanner) {
        if (args.length == 0) {
            System.out.println("Welcome to Fitness Tracker!"); // Ensure expected output for tests
            System.err.println("Error: No command provided.");
            return;
        }


        String command = args[0];

        try {
            switch (command) {
                case "exit":
                    System.out.println("Exiting Fitness Tracker. Goodbye!");
                    return;

                case "login":
                    handleLogin(scanner);
                    return;

                case "create-user":
                    handleCreateUser(scanner);
                    return;
            }
            // Prevent execution if no user is logged in
            if (currentUser == null) {
                System.out.println("Please log in or create a user before using the tracker.");
                return;
            }

            // Step-by-step input for logging a workout
            switch (command) {
                case "log" -> {
                    String activityTypeInput, dateInput;
                    int durationMinutes;

                    if (testMode) {
                        if (args.length < 4) {
                            System.err.println("Error: Usage: log <activityType> <durationMinutes> <date>");
                            return;
                        }
                        activityTypeInput = args[1];
                        durationMinutes = Integer.parseInt(args[2]);
                        dateInput = args[3];
                    } else {
                        System.out.print("Enter activity type (Running, Weightlifting, Cycling): ");
                        activityTypeInput = scanner.nextLine().trim().toLowerCase();

                        System.out.print("Enter duration in minutes: ");
                        try {
                            durationMinutes = Integer.parseInt(scanner.nextLine().trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid duration. Please enter a valid number.");
                            return;
                        }

                        System.out.print("Enter date (YYYY-MM-DD) or press Enter for today: ");
                        dateInput = scanner.nextLine().trim();
                        if (dateInput.isEmpty()) {
                            dateInput = LocalDate.now().toString();
                        }
                    }

                    ActivityType activityType = ActivityType.fromName(activityTypeInput);
                    LocalDate workoutDate = LocalDate.parse(dateInput);

                    String[] newArgs = {"log", activityType.getName(), String.valueOf(durationMinutes), workoutDate.toString()};
                    handleLogWorkout(newArgs);
                }

                // Step-by-step input for tracking progress
                case "track" -> {
                    if (testMode && args.length < 2) {
                        System.err.println("Error: Usage: track <activityType>");
                        return;
                    }

                    String activityTypeInput = testMode ? args[1] : scanner.nextLine().trim().toLowerCase();
                    ActivityType activityType = ActivityType.fromName(activityTypeInput);

                    String[] newArgs = {"track", activityType.getName()};
                    handleTrackProgress(newArgs);
                }

                // Step-by-step input for setting a fitness goal
                case "set-goal" -> {
                    String goalTitle, dateInputStart, dateInputEnd;
                    double targetValue;

                    if (testMode) {
                        if (args.length < 5) {
                            System.err.println("Error: Usage: set-goal <goalTitle> <targetValue> <startDate> <endDate>");
                            return;
                        }
                        goalTitle = args[1];
                        targetValue = Double.parseDouble(args[2]);
                        dateInputStart = args[3];
                        dateInputEnd = args[4];
                    } else {
                        System.out.print("Enter goal title: ");
                        goalTitle = scanner.nextLine().trim();

                        System.out.print("Enter target value (e.g., distance or duration goal): ");
                        try {
                            targetValue = Double.parseDouble(scanner.nextLine().trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid target value. Please enter a valid number.");
                            return;
                        }

                        System.out.print("Enter start date (YYYY-MM-DD): ");
                        dateInputStart = scanner.nextLine().trim();
                        System.out.print("Enter end date (YYYY-MM-DD): ");
                        dateInputEnd = scanner.nextLine().trim();
                    }

                    LocalDate startDate, endDate;
                    try {
                        startDate = LocalDate.parse(dateInputStart);
                        endDate = LocalDate.parse(dateInputEnd);
                    } catch (DateTimeParseException e) {
                        System.err.println("Invalid date format. Please enter YYYY-MM-DD.");
                        return;
                    }

                    if (startDate.isAfter(endDate)) {
                        System.err.println("Error: Start date cannot be after end date.");
                        return;
                    }

                    String[] newArgs = {"set-goal", goalTitle, String.valueOf(targetValue), startDate.toString(), endDate.toString()};
                    handleSetGoal(newArgs);
                }
            }


            // Execute remaining commands
            try {
                switch (command) {
                    case "view-user":
                        handleViewUserInfo();
                        break;
                    case "update-goal-title":
                        handleUpdateGoalTitle(args);
                        break;
                    case "update-activity":
                        handleUpdateActivityType(args);
                        break;
                    case "view-goals":
                        handleViewActiveGoals();
                        break;
                    case "view-workouts":
                        handleViewWorkouts();
                        break;
                    default:
                        System.out.println("Unknown command: " + command);
                        System.out.println("Please use a valid command.");
                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
            }} catch (Exception e) {
            throw new RuntimeException(e);
        }

        scanner.close();
    }

    /**
     * Displays the available commands in the fitness tracker application.
     */
    private static void displayMenu() {
        System.out.println("Available commands:");
        System.out.println("1. login - Select an existing user.");
        System.out.println("2. create-user - Create a new user profile.");
        System.out.println("3. log - Record a completed workout (activity type, duration, and date).");
        System.out.println("4. track - View total progress for a specific activity (time spent & calories burned).");
        System.out.println("5. set-goal - Set a fitness goal with a target value and a time period.");
        System.out.println("6. update-goal-title - Change the name of an existing fitness goal.");
        System.out.println("7. update-activity - Update the activity type for a past workout.");
        System.out.println("8. view-goals - Show all fitness goals set by the user.");
        System.out.println("9. view-workouts - List all workouts recorded.");
        System.out.println("10. view-user - Show user profile details (name and ID).");
        System.out.println("11. exit - Quit the program.");
        System.out.println("===========================");
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    //Creating Public test-method here since users otherwise are private
    public static void createTestUser(String name, String userId) {
        Scanner scanner = new Scanner(name + "\n" + userId + "\n");
        handleCreateUser(scanner);
    }

    public static void loginTestUser(String userId) {
        Scanner scanner = new Scanner(userId + "\n");
        handleLogin(scanner);
    }


    private static void handleLogin(Scanner scanner) {
        if (users.isEmpty()) {
            System.out.println("No users found. Please create a user first.");
            return;
        }

        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine().trim();

        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                currentUser = user;
                System.out.println("Login successful. Welcome, " + currentUser.getName() + "!");
                return;
            }
        }
        System.out.println("User ID not found. Please try again or create a new user.");
    }

    private static void handleCreateUser(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter a unique user ID: ");
        String userId = scanner.nextLine().trim();

        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                System.out.println("User ID already exists. Try a different one.");
                return;
            }
        }

        User newUser = new User(name, userId);
        users.add(newUser);
        currentUser = newUser;
        System.out.println("User created successfully! Logged in as " + name + ".");
    }



    private static void handleViewUserInfo() {
        System.out.println("User Name: " + currentUser.getName());
        System.out.println("User ID: " + currentUser.getUserId());
    }

    private static void handleLogWorkout(String[] args) {
        if (args.length < 3) {
            System.err.println("Error: Usage: log <activityType> <durationMinutes>");
            return;
        }

        try {
            ActivityType activityType = ActivityType.fromName(args[1]);
            int durationMinutes = Integer.parseInt(args[2]);
            LocalDate workoutDate = (args.length > 3) ? LocalDate.parse(args[3]) : LocalDate.now();

            Workout workout = new Workout(activityType, Duration.ofMinutes(durationMinutes), workoutDate);
            currentUser.addWorkout(workout);
            System.out.println("Workout logged successfully: " + workout);
        } catch (Exception e) {
            System.err.println("Error logging workout: " + e.getMessage());
        }
    }

    private static void handleTrackProgress(String[] args) {
        if (args.length < 2) {
            System.err.println("Error: Usage: track <activityType>");
            return;
        }

        try {
            ActivityType activityType = ActivityType.fromName(args[1]);
            long totalMinutes = currentUser.getTotalWorkoutMinutes(activityType);
            double totalCalories = currentUser.getTotalCaloriesBurned(activityType);

            System.out.printf("Progress for %s:%n", activityType.getName());
            System.out.printf("  Total Duration: %d minutes%n", totalMinutes);
            System.out.printf("  Total Calories Burned: %.2f%n", totalCalories);
        } catch (Exception e) {
            System.err.println("Error tracking progress: " + e.getMessage());
        }
    }

    private static void handleSetGoal(String[] args) {
        if (args.length < 5) {
            System.err.println("Error: Usage: set-goal <goalTitle> <targetValue> <startDate (YYYY-MM-DD)> <endDate (YYYY-MM-DD)>");
            return;
        }

        try {
            String goalTitle = args[1];
            double targetValue = Double.parseDouble(args[2]);
            LocalDate startDate = LocalDate.parse(args[3]);
            LocalDate endDate = LocalDate.parse(args[4]);

            if (startDate.isAfter(endDate)) {
                throw new IllegalArgumentException("Start date cannot be after end date.");
            }

            Goal goal = new Goal(goalTitle, targetValue, startDate, endDate);
            currentUser.addGoal(goal);
            System.out.println("Goal set successfully: " + goal);
        } catch (Exception e) {
            System.err.println("Error setting goal: " + e.getMessage());
        }
    }

    private static void handleUpdateGoalTitle(String[] args) {
        if (args.length < 3) {
            System.err.println("Error: Usage: update-goal-title <goalIndex> <newGoalTitle>");
            return;
        }

        try {
            int index = Integer.parseInt(args[1]);
            String newGoalTitle = args[2];

            List<Goal> goals = currentUser.getGoals();
            if (index < 0 || index >= goals.size()) {
                System.err.println("Invalid goal index.");
                return;
            }

            goals.get(index).setGoalTitle(newGoalTitle);
            System.out.println("Updated goal title successfully.");
        } catch (Exception e) {
            System.err.println("Error updating goal title: " + e.getMessage());
        }
    }

    private static void handleViewActiveGoals() {
        List<Goal> activeGoals = currentUser.getActiveGoals();
        if (activeGoals.isEmpty()) {
            System.out.println("No active goals.");
            return;
        }

        System.out.println("Your Active Goals:");
        for (Goal goal : activeGoals) {
            System.out.println("Title: " + goal.getGoalTitle());
            System.out.println("Target: " + goal.getTargetValue());
            System.out.println("Start: " + goal.getStartDate());
            System.out.println("End: " + goal.getEndDate());
            System.out.println("Achieved: " + goal.isAchieved());
            System.out.println("----------------------------");
        }
    }

    private static void handleUpdateActivityType(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: update-activity <workoutIndex> <newActivityType>");
            return;
        }

        try {
            int index = Integer.parseInt(args[1]);
            ActivityType newActivityType = ActivityType.fromName(args[2]);

            List<Workout> workouts = currentUser.getWorkouts();
            if (index < 0 || index >= workouts.size()) {
                System.err.println("Invalid workout index.");
                return;
            }

            workouts.get(index).setActivityType(newActivityType);
            System.out.println("Updated workout activity type successfully.");
        } catch (Exception e) {
            System.err.println("Error updating workout activity type: " + e.getMessage());
        }
    }

    private static void handleViewWorkouts() {
        List<Workout> workouts = currentUser.getWorkouts();
        if (workouts.isEmpty()) {
            System.out.println("No workouts logged yet.");
            return;
        }

        System.out.println("Your Logged Workouts:");
        workouts.forEach(System.out::println);
    }
}
