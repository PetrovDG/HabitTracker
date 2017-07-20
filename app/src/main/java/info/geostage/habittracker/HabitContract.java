package info.geostage.habittracker;

import android.provider.BaseColumns;

/*
 * Created by Dimitar on 10.6.2017 г..
 */

public class HabitContract {

    public static final class HabitEntry implements BaseColumns {
        public static final String HABIT_TABLE_NAME = "habits";
        public static final String COLUMN_HABIT_ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_PLACE = "place";
        public static final String COLUMN_HABIT_DATE = "date";
        public static final String COLUMN_HABIT_START_TIME = "time";
        public static final String COLUMN_HABIT_DURATION = "duration";
    }
}