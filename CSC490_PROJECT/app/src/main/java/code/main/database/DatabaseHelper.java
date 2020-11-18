package code.main.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import code.main.UserModel;

/*
----------------------------------------------------------------------------------------------------
Data structure under code.main.data:

ExerciseDataObject - contains info for individual exercises
    ParentGroup         -   MuscleDataObject (body region, muscle group, and muscle group image)
    ExerciseName        -   String
    ExerciseDescription -   String
    ExerciseType        -   ExerciseTypeEnum (PUSH, PULL, LEG, NONE)
    ExerciseImage       -   Drawable (Android Studio data type)
    ExerciseGif         -   AnimatedImageDrawable (Android Studio data type - not sure of video
                                format, if it's a youtube link then may change to String with url)
MuscleDataObject - contains info on a muscle group's body region and image
    BodyRegion          -   String
    MuscleGroup         -   String
    MuscleGroupImage    -   Drawable (Android Studio data type)


vvv Not ideal but these two will be locally stored vvv

WorkoutDataObject - contains info on pre-made or custom workouts with muscle groups and days
    WorkoutTitle        -   String
    WorkoutDescription  -   String
    Schedule            -   ArrayList<ScheduleContainer> (list of groups with their set days)

IsolationScheduleContainer - helper for WorkoutDataObject, connects a muscle group to days under a specific workout
    SelectedDays        -   ArrayList<Days> (SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY)
    MuscleGroup         -   MuscleDataObject (body region, muscle group, and muscle group image)

PushPullScheduleContainer - helper for WorkoutDataObject, connects a workout type to days under a specific workout
    SelectedDays        -   ArrayList<Days> (SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY)
    DayType             -   ExerciseTypeEnum (PUSH, PULL, LEG, NONE)

----------------------------------------------------------------------------------------------------
What we need from the database:

ExerciseDataObject:
    get everything from a single exercise given an exercise name
    get all the exercises given a muscle group name in alphabetical order
    get all the exercises given an exercise type enum in alphabetical order
MuscleDataObject:
    get a list of all muscle groups available in alphabetical order

----------------------------------------------------------------------------------------------------
 */



public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "workout.db";
    public static final int DATABASE_VERSION = 1;

    //table name
    public static final String BODY_REGION = "body_region";
    public static final String MUSCLE_GROUP = "muscle_group";
    public static final String EXERCISE = "exercise";
    public static final String USERS = "users";

    //region table columns
    public static final String REGION_UID_COL = "region_uid";
    public static final String REGION_NAME_COL = "region_name";
    public static final String  MUSCLE_GROUP_COL = "muscle_group";

    //muscle group columns including muscle group
    public static final String GROUP_UID_COL = "group_uid";
    public static final String GROUP_NAME_COL = "group_name";

    //exercise table columns
    public static final String EXERCISE_UID_COL = "exercise_uid";
    public static final String EXERCISE_NAME_COL = "exercise_name";
    public static final String EXERCISE_DESC_COL = "exercise_desc";
    public static final String EXERCISE_TYPE_COL = "exercise_type";

    //user table columns
    public static final String USER_UID_COL = "user_uid";
    public static final String USER_EMAIL_COL = "user_email";
    public static final String USER_NAME_COL = "user_name";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    //will be called the first time the db is referenced
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + USERS + "( " + USER_UID_COL + " TEXT NOT NULL PRIMARY KEY, " + USER_EMAIL_COL + " TEXT NOT NULL, " + USER_NAME_COL + " TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + BODY_REGION + " ( " + REGION_UID_COL + " INTEGER NOT NULL PRIMARY KEY, " + REGION_NAME_COL + " TEXT NOT NULL, " + MUSCLE_GROUP_COL + " TEXT NOT NULL, FOREIGN KEY ( " + MUSCLE_GROUP_COL + ") REFERENCES " + MUSCLE_GROUP + " ( " + GROUP_NAME_COL + " ) );");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + MUSCLE_GROUP + " ( " + GROUP_UID_COL + " INTEGER NOT NULL PRIMARY KEY, " + GROUP_NAME_COL + " TEXT NOT NULL, " + EXERCISE_UID_COL + " INETEGER NOT NULL, FOREIGN KEY ( " + EXERCISE_UID_COL + ") REFERENCES " + EXERCISE + " ( " + EXERCISE_UID_COL + " ));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + EXERCISE + " ( " + EXERCISE_UID_COL + " INGETER NOT NULL PRIMARY KEY, " + EXERCISE_NAME_COL + " TEXT NOT NULL, " + EXERCISE_DESC_COL + " TEXT, " + EXERCISE_TYPE_COL + " TEXT NOT NULL );");

    }

    //This is for forward compatibility
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(UserModel signInUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_UID_COL, signInUser.getId());
        cv.put(USER_EMAIL_COL, signInUser.getEmail());
        cv.put(USER_NAME_COL, signInUser.getName());

        long insert = db.insert(USERS, null, cv);
        if (insert == -1)
            return false;
        else {
            return true;
        }

    }
}
