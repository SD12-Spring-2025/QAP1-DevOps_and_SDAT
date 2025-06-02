# QAP1-DevOps_and_SDAT
**Fitness Tracker**

Fitness Tracker Application 🚀

A CLI-based fitness tracking system with user management, goal setting, and workout logging!

📌 Project Overview

The Fitness Tracker Application is a command-line fitness tracking tool that allows users to:

✅ Create & log in to accounts

✅ Track workouts & calories burned

✅ Set & manage fitness goals

✅ Monitor progress efficiently

This project is designed to provide a structured way to track fitness activities, while maintaining unit test compatibility for reliability.

-------------------------------------------------------------------------------
🛠 Technologies Used

Java (JDK 17+) – Core programming language

JUnit 5 – Unit testing framework

Maven – Dependency management

IntelliJ IDEA – Recommended IDE for development

-------------------------------------------------------------------------------
🚀 Features

✅ User Management
Create a user account
Log in to an existing account

✅ Workout Tracking
Log a workout (Running, Cycling, Weightlifting)
View all logged workouts
Calculate total calories burned per activity

✅ Goal Setting
Set fitness goals (e.g., distance, calories burned, minutes exercised)
Update progress on goals
Track achievements & completion status

✅ Unit Tests (JUnit 5)
Comprehensive unit tests for Goal, Workout, and FitnessTrackerApp
Tests include both positive & negative scenarios
Error handling validation

-------------------------------------------------------------------------------

📥 Installation & Setup

1️⃣ Clone the Repository

    git clone https://github.com/your-repo-url/FitnessTracker.git
    cd FitnessTracker

2️⃣ Build the Project

    mvn clean install

3️⃣ Run the Application

    java -jar target/FitnessTracker.jar

4️⃣ Run Unit Tests

    mvn test

-------------------------------------------------------------------------------

📝 Usage Instructions

🔹 Available Commands
Command	                                                    Description
login	                                      Log in to an existing user account
create-user	                                Create a new user account
log	                                        Log a workout session
track	                                      View progress for a specific workout type
set-goal	                                  Set a new fitness goal
view-goals	                                View active fitness goals
view-workouts	                              View all logged workouts
view-user	                                  Display user information
exit	                                      Close the application

🔹 Example Usage
🔸 Logging a Workout

    > log Running 45 2024-06-01
    Workout logged successfully: Running, Duration: 45 minutes, Date: 2024-06-01, Calories Burned: 4.5

🔸 Setting a Goal

    > set-goal Weekly Running 150 2024-06-01 2024-06-07
    Goal set successfully: Weekly Running, Target: 150, Period: 2024-06-01 to 2024-06-

🔸 Tracking Progress

    > track Running
    Progress for Running:
    Total Duration: 120 minutes
    Total Calories Burned: 12.0

-------------------------------------------------------------------------------


📌 Unit Testing Strategy
JUnit 5 tests are used to validate: ✅ Workout calorie calculation ✅ Goal achievement tracking ✅ Error handling & validations ✅ Negative case scenarios (invalid input, zero values, etc.)

Test Classes

Test Class	                                   Purpose
FitnessTrackerAppTest	                        Validates command execution & error handling
GoalTest	                                    Tests goal creation, progress updates, and validations
WorkoutTest	                                  Tests workout logging & calorie calculations

-------------------------------------------------------------------------------
🔗 Contributors & Credits
Wayne – Lead Developer 🚀
JUnit & Maven Community – Testing & build tools
IntelliJ IDEA – Recommended development environment

-------------------------------------------------------------------------------

🔧 Future Improvements
🔹 Add database storage (save workouts persistently) 
🔹 Introduce advanced analytics (graphs, trends) 
🔹 Expand available workouts (swimming, hiking, yoga)
🔹 Improved front end-user interface


-------------------------------------------------------------------------------
📩 Feedback & Support
If you encounter any issues or have suggestions, feel free to open an issue or contact the contributors.

Happy tracking! 🏋️‍♂️🎯🚀




