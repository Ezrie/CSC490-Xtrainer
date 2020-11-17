package code.main.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

    public static final String BODY_REGION = "body_region";
    public static final String MUSCLE_GROUP = "muscle_group";
    public static final String EXERCISE = "exercise";

    public static final String REGION_UID_COL = "region_uid";
    public static final String REGION_NAME_COL = "region_name";
    public static final String  MUSCLE_GROUP_COL = "muscle_group";

    public static final String GROUP_UID_COL = "group_uid";
    public static final String GROUP_NAME_COL = "group_name";
    public static final String GROUP_IMG_COL = "group_img";

    public static final String EXERCISE_UID_COL = "exercise_uid";
    public static final String EXERCISE_NAME_COL = "exercise_name";
    public static final String EXERCISE_DESC_COL = "exercise_desc";
    public static final String EXERCISE_TYPE_COL = "exercise_type";
    public static final String EXERCISE_IMG_COL = "exercsie_img";
    public static final String EXERCISE_VIDEO_COL ="exercise_video";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+BODY_REGION+" ( "+REGION_UID_COL+" INTEGER NOT NULL PRIMARY KEY, "+REGION_NAME_COL+" TEXT NOT NULL, "+ MUSCLE_GROUP_COL+" TEXT NOT NULL, FOREIGN KEY ("+MUSCLE_GROUP_COL+") REFERENCES "+MUSCLE_GROUP+" ("+GROUP_NAME_COL+") );");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+MUSCLE_GROUP+" ( "+GROUP_UID_COL+" INTEGER NOT NULL PRIMARY KEY, "+GROUP_NAME_COL+" TEXT NOT NULL, "+GROUP_IMG_COL+" BLOB, "+EXERCISE_UID_COL+" INETEGER NOT NULL, FOREIGN KEY ("+EXERCISE_UID_COL+") REFERENCES "+EXERCISE+" ("+EXERCISE_UID_COL+"));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+EXERCISE+" ( "+EXERCISE_UID_COL+" INGETER NOT NULL PRIMARY KEY, "+EXERCISE_NAME_COL+" TEXT NOT NULL, "+EXERCISE_DESC_COL+" TEXT, "+EXERCISE_TYPE_COL+" TEXT NOT NULL, "+EXERCISE_IMG_COL+" BLOB, "+EXERCISE_VIDEO_COL+" TEXT );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
