package code.main.database;

import android.content.Context;

public class DatabaseAdapter {
    private DatabaseHelper myDb;

    public DatabaseAdapter (Context context) {
        myDb = new DatabaseHelper(context);
    }

    public DatabaseHelper getDatabase () {
        return myDb;
    }
}
