package com.example.android.habittracker.db;

import android.provider.BaseColumns;

public class HabitContract {

    public HabitContract(){}

    public static abstract class HabitEntry implements BaseColumns{
        public static final String TABLE_NAME = "habit_tracker";
        public static final String HABIT_ID = "habit_ID";
        public static final String NAME = "habit_name";
        public static final String DURATION = "duration";
    }
}
