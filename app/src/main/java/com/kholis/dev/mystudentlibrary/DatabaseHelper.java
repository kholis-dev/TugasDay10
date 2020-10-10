package com.kholis.dev.mystudentlibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENTS = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "name";
    public static final String TAG = DatabaseHelper.class.getSimpleName();

    public static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "+TABLE_STUDENTS+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_FIRSTNAME+" TEXT );";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "DatabaseHelper: "+CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '"+TABLE_STUDENTS+"'");
        onCreate(sqLiteDatabase);
    }

    public long addStudentDetails(String student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(KEY_FIRSTNAME,student);
        return db.insert(TABLE_STUDENTS,null,data);
    }

    public ArrayList<String> getAllStudent(){
        ArrayList<String> studentsArr = new ArrayList<>();
        String name="";
        String select = "SELECT * FROM "+ TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(select,null);
        if (c.moveToFirst()){
            do {
                name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
                studentsArr.add(name);
            } while (c.moveToNext());
            Log.d(TAG, "getAllStudent: "+studentsArr.toString());
        }
        return studentsArr;
    }
}
