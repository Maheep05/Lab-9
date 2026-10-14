package com.example.experiment9

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var usnEditText: EditText
    private lateinit var courseEditText: EditText

    private lateinit var rememberCheckBox: CheckBox

    private lateinit var savePreferencesButton: Button
    private lateinit var addStudentButton: Button
    private lateinit var viewStudentsButton: Button

    private lateinit var studentsTextView: TextView

    private lateinit var databaseHelper: DatabaseHelper

    private lateinit var sharedPreferences:
            SharedPreferences

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_main
        )

        // Initialize Views

        nameEditText =
            findViewById(R.id.nameEditText)

        usnEditText =
            findViewById(R.id.usnEditText)

        courseEditText =
            findViewById(R.id.courseEditText)

        rememberCheckBox =
            findViewById(R.id.rememberCheckBox)

        savePreferencesButton =
            findViewById(
                R.id.savePreferencesButton
            )

        addStudentButton =
            findViewById(
                R.id.addStudentButton
            )

        viewStudentsButton =
            findViewById(
                R.id.viewStudentsButton
            )

        studentsTextView =
            findViewById(
                R.id.studentsTextView
            )

        // SharedPreferences

        sharedPreferences =
            getSharedPreferences(
                "StudentPreferences",
                Context.MODE_PRIVATE
            )

        // SQLite

        databaseHelper =
            DatabaseHelper(this)

        // Load previously saved preferences

        loadPreferences()

        // Button listeners

        savePreferencesButton.setOnClickListener {
            savePreferences()
        }

        addStudentButton.setOnClickListener {
            addStudent()
        }

        viewStudentsButton.setOnClickListener {
            viewStudents()
        }
    }

    // -----------------------------
    // SharedPreferences
    // -----------------------------

    private fun savePreferences() {

        val name =
            nameEditText.text
                .toString()
                .trim()

        val usn =
            usnEditText.text
                .toString()
                .trim()

        val remember =
            rememberCheckBox.isChecked

        if (
            name.isEmpty() ||
            usn.isEmpty()
        ) {

            Toast.makeText(
                this,
                "Please enter name and USN",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        sharedPreferences
            .edit()
            .putString(
                "name",
                name
            )
            .putString(
                "usn",
                usn
            )
            .putBoolean(
                "remember",
                remember
            )
            .apply()

        Toast.makeText(
            this,
            "Preferences saved",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun loadPreferences() {

        val name =
            sharedPreferences.getString(
                "name",
                ""
            )

        val usn =
            sharedPreferences.getString(
                "usn",
                ""
            )

        val remember =
            sharedPreferences.getBoolean(
                "remember",
                false
            )

        nameEditText.setText(name)

        usnEditText.setText(usn)

        rememberCheckBox.isChecked =
            remember
    }

    // -----------------------------
    // SQLite
    // -----------------------------

    private fun addStudent() {

        val name =
            nameEditText.text
                .toString()
                .trim()

        val usn =
            usnEditText.text
                .toString()
                .trim()

        val course =
            courseEditText.text
                .toString()
                .trim()

        if (
            name.isEmpty() ||
            usn.isEmpty() ||
            course.isEmpty()
        ) {

            Toast.makeText(
                this,
                "Please enter all details",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val success =
            databaseHelper.insertStudent(
                name,
                usn,
                course
            )

        if (success) {

            Toast.makeText(
                this,
                "Student added successfully",
                Toast.LENGTH_SHORT
            ).show()

            courseEditText.text.clear()

        } else {

            Toast.makeText(
                this,
                "Failed to add student",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun viewStudents() {

        val students =
            databaseHelper.getStudents()

        studentsTextView.text =
            students
    }
}