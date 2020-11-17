package code.main.data;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SaveFile {

    private static FileOutputStream fos = null;
    private static String fileName = "saveState.txt";

    public static void createObject(Context _ctx) {

        try {
            //Will create file if non-existent. Otherwise will open it.
            fos = _ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //If stream is open, close it (prevent memory leaks).
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readObject(Context _ctx) {

        //Opens file, parses data from file and inserts into linked list.
        String dataFromFile = "";

        //FileInputStream is used to write to the file.
        FileInputStream fis = null;

        try {
            fis = _ctx.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            dataFromFile = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataFromFile;
    }

    public static void updateObject(Context _ctx, String _selectedWorkout) {

        try {
            //Will delete content if there's any. Otherwise will open a empty file.
            fos = _ctx.openFileOutput(fileName, Context.MODE_PRIVATE);

            //Write to file
            fos.write(_selectedWorkout.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //If stream is open, close it (prevent memory leaks).
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteObject(Context _ctx, String _file) {

        String fileName = _file + ".txt";

        try {
            //Will delete content if there's any.
            fos = _ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
