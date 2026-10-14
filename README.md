Experiment 9 — Data Persistence using SharedPreferences and SQLite
1. AIM
To demonstrate data persistence in an Android application using SharedPreferences and SQLite Database.
2. Concept / Technology
SharedPreferences
SharedPreferences is used to store small amounts of persistent key-value data.
In this experiment, it is used to store:
- Student Name
- Student USN
- Remember Me preference
SQLite
SQLite is a lightweight relational database provided by Android.
It is used to store student records containing:
- ID
- Name
- USN
- Course
3. Scenario
A Student Data Persistence Application is developed.
The application allows a student to:
1. Save their Name and USN using SharedPreferences.
2. Store student records using SQLite.
3. Retrieve saved data after closing and reopening the application.
4. Technologies Used
- Android Studio
- Kotlin
- XML
- Android SDK
- SharedPreferences
- SQLite
- SQLiteOpenHelper
5. Features
- Save student Name and USN using SharedPreferences.
- Retrieve saved preferences after restarting the application.
- Add student records to SQLite.
- View stored student records.
- Verify data persistence.
6. Project Structure
Experiment9/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/experiment9/
│           │   ├── MainActivity.kt
│           │   └── DatabaseHelper.kt
│           │
│           ├── res/
│           │   ├── layout/
│           │   │   └── activity_main.xml
│           │   └── values/
│           │
│           └── AndroidManifest.xml
│
├── screenshots/
│   ├── output.png
│   ├── test-case-1-usn-name.png
│   ├── test-case-2-sqlite.png
│   └── test-case-3-persistence.png
│
└── README.md


7. Application Output
The application provides a user interface for saving student information using SharedPreferences and storing student records using SQLite.


<img width="365" height="776" alt="Screenshot 2026-10-15 at 12 11 24 AM" src="https://github.com/user-attachments/assets/4bf8feb4-c588-467e-b4c1-7ace9e4ba529" />

13. Result
Successfully implemented and tested an Android application demonstrating data persistence using SharedPreferences and SQLite.
