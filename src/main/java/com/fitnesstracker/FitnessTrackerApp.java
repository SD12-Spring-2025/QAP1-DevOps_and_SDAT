package com.fitnesstracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CLI-based Fitness Tracker with multi-user support.
 */
public class FitnessTrackerApp {
    private static final List<User> users = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Fitness Tracker!");
        System.out.println("===========================");

        while (true) {
            displayMenu();
            System.out.print("\nEnter command: ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "exit":
                    System.out.println("Exiting Fitness Tracker. Goodbye!");
                    return;

                case "login":
                    handleLogin(scanner);
                    break;

                case "create-user":
                    handleCreateUser(scanner);
                    break;

                default:
                    if (currentUser == null) {
                        System.out.println("Please log in or create a user first.");
                        break;
                    }
                    handleCommand(command, scanner);
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Available commands:");
        System.out.println("login - Select an existing user.");
        System.out.println("create-user - Create a new user profile.");
        System.out.println("log - Record a workout (activity type & duration).");
        System.out.println("track - View total progress for an activity.");
        System.out.println("set-goal - Set a fitness goal.");
        System.out.println("view-goals - View fitness goals.");
        System.out.println("view-workouts - View logged workouts.");
        System.out.println("view-user - Show user profile.");
        System.out.println("exit - Quit the program.");
    }

    private static void handleLogin(Scanner scanner) {
        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine().trim();

        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                currentUser = user;
                System.out.println("Login successful. Welcome, " + currentUser.getName() + "!");
                return;
            }
        }
        System.out.println("User not found. Please try again or create a new user.");
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

    private static void handleCommand(String command, Scanner scanner) {
        switch (command) {
            case "log":
                handleLogWorkout(getWorkoutDetails(scanner));
                break;
            case "track":
                handleTrackProgress(getActivityType(scanner));
                break;
            case "set-goal":
                handleSetGoal(getGoalDetails(scanner));
                break;
            case "view-user":
                handleViewUserInfo();
                break;
            case "view-goals":
                handleViewActiveGoals();
                break;
            case "view-workouts":
                handleViewWorkouts();
                break;
            default:
                System.out.println("Unknown command. Please try again.");
        }
    }

    // Placeholder methods for logic implementation
    private static String[] getWorkoutDetails(Scanner scanner) { return new String[0]; }
    private static String[] getActivityType(Scanner scanner) {  return new String[0]; }
    private static String[] getGoalDetails(Scanner scanner) {  return new String[0]; }
    private static void handleLogWorkout(String[] args) {  }
    private static void handleTrackProgress(String[] args) {  }
    private static void handleSetGoal(String[] args) { }
    private static void handleViewUserInfo() { }
    private static void handleViewActiveGoals() {  }
    private static void handleViewWorkouts() { }
}
