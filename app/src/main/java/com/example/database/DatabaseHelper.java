package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "Currencies.db";
    public static int DB_VERSION = 1;

    public static String TABLE_NAME = "SaveCurrency";
    public static String COL_ID = "_id";
    public static final String COL2_NAME = "countries";
    public static final String COL3_NAME = "currencies";
    //SQL query
    /*private static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "( "
            + COL_ID + "INTEGER AUTO_INCREMENT PRIMARY KEY,"

            + COL2_NAME + " TEXT, "
            + COL3_NAME + "TEXT);"
            ;*/

    private static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            +COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COL2_NAME+ " TEXT, "
            + COL3_NAME+ " TEXT );"
            ;




    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
