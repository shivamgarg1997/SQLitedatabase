package com.example.shivam.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FMS.db";
    public static final String TABLE_NAME_1 = "person_table";
    public static final String COL_11 = "NAME";
    public static final String COL_12 = "PASSWORD";
    public static final String COL_13 = "NUMBER";

    public static final String TABLE_NAME_2 = "order_table";
    public static final String COL_21 = "FIRM";
    public static final String COL_22 = "QUANTITY";
    public static final String COL_23 = "AGENT";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
    public Cursor getdata(String log , String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_1 + "where name ='" + log + "' and password ='" +
                pass + "'",null);
        return res;
    }
    public List<String> getAllNames(){
        List<String> names = new ArrayList<String>();
        String selectQuery = "SELECT" + COL_21 + "FROM " + TABLE_NAME_2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(selectQuery, null);
        if (res.moveToFirst()) {
            do {
                names.add(res.getString(1));
            } while (res.moveToNext());
        }
        res.close();
        db.close();
        return names;
    }

    public StringBuffer getorders(String name , String pass){
        List<String> names = new ArrayList<String>();
        String selectQuery = "SELECT" + COL_21 + "," + COL_22 + "FROM " + TABLE_NAME_2 + "where NAME = '" +name +
                "' and password = '" + pass +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(selectQuery, null);
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(0)+"\n");
            buffer.append(res.getString(1)+"\n\n");
        }
        res.close();
        db.close();
        return buffer;
    }
    public boolean insertData(String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_11,name);
        contentValues.put(COL_12,name);
        contentValues.put(COL_13,surname);
        long result = db.insert(TABLE_NAME_1,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean insertorder(String firm,String quan,String agent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_21,firm);
        contentValues.put(COL_22,quan);
        contentValues.put(COL_23,agent);
        long result = db.insert(TABLE_NAME_2,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_1 + "(NAME TEXT ,PASSWORD TEXT,NUMBER TEXT);");
        db.execSQL("create table " + TABLE_NAME_2 + "(FIRM TEXT ,QUANTITY TEXT,AGENT TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXITS " + TABLE_NAME_2);
    }
}
