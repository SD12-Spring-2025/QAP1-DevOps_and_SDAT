package com.fitnesstracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link FitnessTrackerApp} class.
 */
class FitnessTrackerAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        // Enable test mode for faster execution
        FitnessTrackerApp.testMode = true;

        // Ensure test user is created & logged in
        FitnessTrackerApp.createTestUser("TestUser", "User123");
        FitnessTrackerApp.loginTestUser("User123");

        assertNotNull(FitnessTrackerApp.getCurrentUser(), "User should be initialized before tests.");
        FitnessTrackerApp.getCurrentUser().getWorkouts().clear();
        FitnessTrackerApp.getCurrentUser().getGoals().clear();
    }

    /**
     * Resets System. out and System. err to their original streams after each test method.
     */
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Tests the 'log' command with valid arguments.
     */
    @Test
    void testMain_logWorkoutCommand_success() {
        String[] args = {"log", "Running", "60", "2024-05-20"};
        FitnessTrackerApp.executeCommand(args, new Scanner(""));

        String expectedOutput = "Workout logged successfully: Activity: Running, Duration: 60 minutes, Date: 2024-05-20, Calories Burned: 600.00";

        assertTrue(outContent.toString().contains(expectedOutput));
    }

    /**
     * Tests the 'log' command with missing arguments.
     */
    @Test
    void testMain_logWorkoutCommand_missingArguments() {
        String[] args = {"log", "Running"}; // Missing duration
        FitnessTrackerApp.executeCommand(args, new Scanner(""));

        assertTrue(errContent.toString().contains("Error: Usage: log <activityType> <durationMinutes>"));
    }

    /**
     * Tests the 'track' command when no workouts exist.
     */
    @Test
    void testMain_trackProgress_noWorkouts() {
        String[] args = {"track", "Running"};
        FitnessTrackerApp.executeCommand(args, new Scanner(""));

        assertTrue(outContent.toString().contains("Progress for Running:"));
        assertTrue(outContent.toString().contains("Total Duration: 0 minutes"));
    }

    /**
     * Tests the application with no command-line arguments.
     */
    @Test
    void testMain_noArguments() {
        String[] args = {};
        FitnessTrackerApp.executeCommand(args, new Scanner(""));

        assertTrue(outContent.toString().contains("Welcome to Fitness Tracker!"));
        assertTrue(errContent.toString().contains("Error: No command provided."));
    }


    /**
     * Tests the application with an unknown command.
     */
    @Test
    void testMain_unknownCommand() {
        String[] args = {"unknown-command"};
        FitnessTrackerApp.executeCommand(args, new Scanner(""));

        assertTrue(outContent.toString().contains("Unknown command: unknown-command"));
    }
}
