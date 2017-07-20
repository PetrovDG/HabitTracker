package info.geostage.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);
        /*
        * Helper method to insert hardcoded habits data into the database. For debugging purposes only.
        */
        HabitDbHelper dbHelper = new HabitDbHelper(this);

        // Create a ContentValues object where column names are the keys,
        // and habits attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, "Bicycling");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_PLACE, "Park");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DATE, "07/07/2017");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_START_TIME, 14);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DURATION, 2);
        dbHelper.insertHabits(values);

        /*
         * Use log messages to check for the insertion.
         */
        Cursor cursor = dbHelper.readHabits(1);
        Log.v("HabitActivity", cursor.toString());
    }
}