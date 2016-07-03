package com.example.android.habittracker.db;


import android.provider.BaseColumns;

public class HabitContract {

    public HabitContract(){}

    public static abstract class HabitEntry implements BaseColumns{
        public static final String TABLE_NAME = "habit_tracker";
        public static final String COLUMN_NAME_HABIT_ID = "habit_ID";
        public static final String COLUMN_NAME_HABIT_NAME = "habit_name";
        public static final String COLUMN_NAME_DURATION = "duration";
    }

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;
}
