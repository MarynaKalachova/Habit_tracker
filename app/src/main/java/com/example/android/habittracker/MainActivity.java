package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.habittracker.db.HabitContract;
import com.example.android.habittracker.db.HabitTrackerDBHelper;

public class MainActivity extends AppCompatActivity {

    HabitTrackerDBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new HabitTrackerDBHelper(this);

        addHabit(1, "Java", 20);
        addHabit(2, "Android", 50);
        addHabit(3, "C++", 10);
        addHabit(4, "Gym", 40);
        addHabit(5, "Read books", 15);

        viewSelectedHabits(30);

        deleteEntries();

        addHabit(1, "Dancing", 40);
        addHabit(2, "Piano", 50);
        addHabit(3, "Guitar", 35);

        viewSelectedHabits(35);

        editHabit(36);
        viewSelectedHabits(35);

        deleteEntries();
    }

    //Insert method that adds a row to the database
    public void addHabit(int id, String name, int duration) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.HABIT_ID, id);
        values.put(HabitContract.HabitEntry.NAME, name);
        values.put(HabitContract.HabitEntry.DURATION, duration);

        db.insert(HabitContract.HabitEntry.TABLE_NAME, "null", values);
    }

    //update method that finds an entry with habit_namy Guitar and sets its duration to newDuration.
    public void editHabit(int newDuration) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.DURATION, newDuration);
        String where = "habit_name =?";
        String[] whereArgs = {"Guitar"};
        db.update(HabitContract.HabitEntry.TABLE_NAME, values, where, whereArgs);
    }

    //read method that reads habit_name and duration of all entries with duration more than int duration
    public void viewSelectedHabits(int duration) {
        db = dbHelper.getReadableDatabase();
        String where = "duration > " + duration;
        String result = "";
        StringBuilder sb = new StringBuilder();
        String[] projection = {HabitContract.HabitEntry.NAME, HabitContract.HabitEntry.DURATION};
        Cursor c = db.query(HabitContract.HabitEntry.TABLE_NAME, projection, where, null, null, null, null);
        c.moveToFirst();
        if (c != null) {
            do {
                for (int i = 0; i < c.getColumnCount(); i++) {
                    result = sb.append(" " + c.getString(i)).toString();
                }
            } while (c.moveToNext());
            Log.v("Result of query ", result);
        }
    }

    //deletes all the entries from the table
    public void deleteEntries() {
        db.delete(HabitContract.HabitEntry.TABLE_NAME, null, null);
    }
}
