package com.example.experiment9

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {

    companion object {

        private const val DATABASE_NAME =
            "StudentDatabase.db"

        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "students"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_USN = "usn"
        const val COLUMN_COURSE = "course"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_USN TEXT NOT NULL,
                $COLUMN_COURSE TEXT NOT NULL
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

        db.execSQL(
            "DROP TABLE IF EXISTS $TABLE_NAME"
        )

        onCreate(db)
    }

    fun insertStudent(
        name: String,
        usn: String,
        course: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put(COLUMN_NAME, name)
        values.put(COLUMN_USN, usn)
        values.put(COLUMN_COURSE, course)

        val result = db.insert(
            TABLE_NAME,
            null,
            values
        )

        return result != -1L
    }

    fun getStudents(): String {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME",
            null
        )

        val result = StringBuilder()

        while (cursor.moveToNext()) {

            val id = cursor.getInt(
                cursor.getColumnIndexOrThrow(
                    COLUMN_ID
                )
            )

            val name = cursor.getString(
                cursor.getColumnIndexOrThrow(
                    COLUMN_NAME
                )
            )

            val usn = cursor.getString(
                cursor.getColumnIndexOrThrow(
                    COLUMN_USN
                )
            )

            val course = cursor.getString(
                cursor.getColumnIndexOrThrow(
                    COLUMN_COURSE
                )
            )

            result.append(
                "$id. $name | $usn | $course\n"
            )
        }

        cursor.close()

        return if (result.isEmpty()) {
            "No students found."
        } else {
            result.toString()
        }
    }
}