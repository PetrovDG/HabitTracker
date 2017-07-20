package info.geostage.habittracker;

/*
 * Created by Dimitar on 10.6.2017 г..
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import info.geostage.habittracker.HabitContract.HabitEntry;

/**
 * Database helper for Habit Tracker app. Manages database creation and version management.
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "habits.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitDbHelper}.
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // Get writable database
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the habits table
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE "
                + HabitEntry.HABIT_TABLE_NAME + "("
                + HabitEntry.COLUMN_HABIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_PLACE + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_START_TIME + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_HABIT_DURATION + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
        // But You can consider actually implementing this method,
        // a very simple implementation is to just drop the current table if it exists and then call onCreate()
        //like this:
        db.execSQL("DROP TABLE IF EXISTS " + HabitEntry.HABIT_TABLE_NAME);
        onCreate(db);
    }

    // Insert data into database with the given values
    public void insertHabits(ContentValues contentValues) {
        database = getWritableDatabase();
        database.insert(HabitEntry.HABIT_TABLE_NAME, null, contentValues);
    }

    public Cursor readHabits(int id) {
        // Get readable database
        database = getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // For every "?" in the selection, we need to have an element in the selection
        // arguments that will fill in the "?". Since we have 1 question mark in the
        // selection, we have 1 String in the selection arguments' String array.
        String selection = HabitEntry.COLUMN_HABIT_ID + " =?";
        String[] selectionArgs = new String[]{Integer.toString(id)};

        // This will perform a query on the habits table directly with the given
        // selection and selection arguments. The cursor
        // could contain multiple rows of the habits table.
        cursor = database.query(HabitEntry.HABIT_TABLE_NAME, null, selection, selectionArgs,
                null, null, null, null);
        cursor.moveToFirst();
        //Close the database
        database.close();
        // Return the cursor
        return cursor;
    }
}
