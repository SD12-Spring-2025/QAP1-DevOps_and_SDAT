package com.fitnesstracker;

import java.util.Scanner;

/**
 * CLI-based Fitness Tracker with workout logging and progress tracking.
 */
public class FitnessTrackerApp {
    private static User currentUser = new User("John Doe", "user123");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Fitness Tracker!");
        System.out.println("===========================");
        displayMenu();

        while (true) {
            System.out.print("\nEnter command: ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("exit")) {
                System.out.println("Exiting Fitness Tracker. Goodbye!");
                break;
            }

            switch (command) {
                case "log":
                    handleLogWorkout(scanner);
                    break;
                case "track":
                    handleTrackProgress(scanner);
                    break;
                case "set-goal":
                    handleSetGoal(scanner);
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

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Available commands:");
        System.out.println("log - Record a workout (activity type & duration).");
        System.out.println("track - View total progress for an activity.");
        System.out.println("set-goal - Set a fitness goal.");
        System.out.println("view-goals - View fitness goals.");
        System.out.println("view-workouts - View logged workouts.");
        System.out.println("exit - Quit the program.");
    }

    // Placeholder methods for logic implementation
    private static void handleLogWorkout(Scanner scanner) { }
    private static void handleTrackProgress(Scanner scanner) { }
    private static void handleSetGoal(Scanner scanner) { }
    private static void handleViewActiveGoals() {  }
    private static void handleViewWorkouts() { }
}