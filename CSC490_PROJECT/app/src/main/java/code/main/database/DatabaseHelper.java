package code.main.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "workout.db";
    private static final int DATABASE_VERSION = 1;

    private static final String BODY_REGION = "body_region";
    private static final String MUSCLE_GROUP = "muscle_group";
    private static final String EXERCISE = "exercise";

    private static final String REGION_UID_COL = "region_uid";
    private static final String REGION_NAME_COL = "region_name";
    private static final String  MUSCLE_GROUP_COL = "muscle_group";

    private static final String GROUP_UID_COL = "group_uid";
    private static final String GROUP_NAME_COL = "group_name";
    private static final String GROUP_IMG_COL = "group_img";

    private static final String EXERCISE_UID_COL = "exercise_uid";
    private static final String EXERCISE_NAME_COL = "exercise_name";
    private static final String EXERCISE_DESC_COL = "exercise_desc";
    private static final String EXERCISE_TYPE_COL = "exercise_type";
    private static final String EXERCISE_IMG_COL = "exercsie_img";
    private static final String EXERCISE_VIDEO_COL ="exercise_video";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+BODY_REGION+" ( "+REGION_UID_COL+" INTEGER NOT NULL PRIMARY KEY, "+REGION_NAME_COL+" TEXT NOT NULL, "+ MUSCLE_GROUP_COL+" TEXT NOT NULL, FOREIGN KEY ("+MUSCLE_GROUP_COL+") REFERENCES "+MUSCLE_GROUP+" ("+GROUP_NAME_COL+") );");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+MUSCLE_GROUP+" ( "+GROUP_UID_COL+" INTEGER NOT NULL PRIMARY KEY, "+GROUP_NAME_COL+" TEXT NOT NULL, "+GROUP_IMG_COL+" BLOB, "+EXERCISE_UID_COL+" INETEGER NOT NULL, FOREIGN KEY ("+EXERCISE_UID_COL+") REFERENCES "+EXERCISE+" ("+EXERCISE_UID_COL+"));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+EXERCISE+" ( "+EXERCISE_UID_COL+" INGETER NOT NULL PRIMARY KEY, "+EXERCISE_NAME_COL+" TEXT NOT NULL, "+EXERCISE_DESC_COL+" TEXT, "+EXERCISE_TYPE_COL+" TEXT NOT NULL, "+EXERCISE_IMG_COL+" BLOB, "+EXERCISE_VIDEO_COL+" TEXT );");
        this.populate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void populate(SQLiteDatabase sqLiteDatabase) {

    }

    /*-------------------------------------------------------------------------------------------

    This is the set of functions that can be called to retrieve data from the database.
    The return type is a Cursor, which you can use to navigate the results from the SQLITE call
    All functions need a database to query.
    The general structure can give you a guide to write more functions as needed.

    There is a need to populate the database. The populate function needs to be implemented.
    All it needs to do is to insert the information on the database using INSERT.
    After that is done, all of the functions bellow are useful.
    -------------------------------------------------------------------------------------------*/
    public Cursor getExerciseByName (SQLiteDatabase sqLiteDatabase, String name) {
        return sqLiteDatabase.rawQuery("SELECT * FROM EXERCISE WHERE EXERCISE.exercise_name = " + name + ";", null);
    }

    public Cursor getExercisesByMuscleGroup (SQLiteDatabase sqLiteDatabase, String name) {
        return sqLiteDatabase.rawQuery("SELECT * FROM EXERCISE INNER JOIN MUSCLE_GROUP ON EXERCISE.EXERCISE_UID = MUSCLE_GROUP.EXERCISE_UID WHERE MUSCLE_GROUP.GOURP_NAME = "+ name + "ORDER BY EXERCISE.EXERCISE_NAME ASC;", null);
    }

    public Cursor getExerciseByType (SQLiteDatabase sqLiteDatabase, String name) {
        return sqLiteDatabase.rawQuery("SELECT * FROM EXERCISE  WHERE EXERCISE.EXERCISE_TYPE = " + name + "ORDER BY EXERCISE.EXERCISE_NAME ASC;", null);
    }

    public Cursor getMuscleGroups(SQLiteDatabase sqLiteDatabase) {
        return sqLiteDatabase.rawQuery("SELECT * FROM MUSCLE_GROUP ORDER BY MUSCLE_GROUP.GROUP_NAME;", null);
    }
}
