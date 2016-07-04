package com.example.android.habittracker.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HabitTrackerDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "HabitTracker.db";

    public HabitTrackerDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " (" +
                        HabitContract.HabitEntry.HABIT_ID + " INTEGER PRIMARY KEY," +
                        HabitContract.HabitEntry.NAME + " TEXT" + ", " +
                        HabitContract.HabitEntry.DURATION + " INTEGER" + " )";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + HabitContract.HabitEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);

    }

    //deletes database
    public void deleteDB(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}