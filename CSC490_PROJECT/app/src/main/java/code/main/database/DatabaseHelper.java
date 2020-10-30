package code.main.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
