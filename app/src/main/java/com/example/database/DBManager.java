package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

public class DBManager {

    DatabaseHelper dbhelper;
    SQLiteDatabase database;
    Context context;



    public DBManager(Context context){
        this.context = context;
    }

    public DBManager Open(){
        dbhelper = new DatabaseHelper(context);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void Close(){
        dbhelper.close();
    }

    public void Insert(String countries, String currencies){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL2_NAME, countries);
        contentValues.put(DatabaseHelper.COL3_NAME, currencies);

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    //my code
    /*public void Update(String colid, String newCountry, String newCurrency){
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(DatabaseHelper.COL2_NAME, newCountry);
        contentValues2.put(DatabaseHelper.COL3_NAME, newCurrency);
        database.update(DatabaseHelper.TABLE_NAME, contentValues2, DatabaseHelper.COL_ID + " = " + colid, null);

    }*/

    //my code
    /*public void Delete(String colId){
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COL_ID + " = " + colId, null);

    }*/

    public Cursor fetch(){
        String[] columns = new String[]{
                DatabaseHelper.COL_ID,
                DatabaseHelper.COL2_NAME,
                DatabaseHelper.COL3_NAME
        };

        Cursor query = database.query(DatabaseHelper.TABLE_NAME, columns,
                null, null, null, null, null);

        if(query != null){
            query.moveToFirst();
        }
        return query;
    }

    public void Update(Long id, String country, String currency) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL2_NAME, country);
        contentValues.put(DatabaseHelper.COL3_NAME, currency);

        database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.COL_ID + " = " + id, null);
    }

    public void Delete(Long id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COL_ID + " = ? ", new String[]{ id.toString() });
        //? - wildcard, in place of wildcard it place selectionArgs[] value
    }
}
