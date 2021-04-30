package com.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "user.db";//Database Name
    public static final int VERSION = 1;//Database version
    public static final String TABLE_NAME = "User_table";//table name
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FIRST_NAME";
    public static final String COL_3 = "LAST_NAME";
    public static final String COL_4 = "MARKS";
    public static final String COL_5 = "Email";
    public static final String COL_6 = "Password";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , FIRST_NAME TEXT , LAST_NAME TEXT, MARKS INTEGER,EMAIL TEXT ,PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String fName, String lName, String marks, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, fName); //key value pair
        cv.put(COL_3, lName);
        cv.put(COL_4, marks);
        cv.put(COL_5, email);
        cv.put(COL_6, password);

        long success = db.insert(TABLE_NAME, null, cv); // true , false = -1
        if (success == -1) {
            return false;
        } else {
            return true;
        }
    }


}

