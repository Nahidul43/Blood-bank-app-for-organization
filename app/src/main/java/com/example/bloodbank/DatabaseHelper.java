package com.example.bloodbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "MY_DATABASE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table myTable (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, class TEXT, age TEXT, groupe TEXT, number TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists myTable");
    }

    public void insertData(String name, String classValue, String age, String groupe, String number) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("class", classValue);
        values.put("age", age);
        values.put("groupe", groupe);
        values.put("number", number);
        database.insert("myTable", null, values);
        database.close();

    }

    public Cursor GetAllData(String name){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("Select * from myTable where groupe like '"+name+"'",null);
        return cursor;



    }


    public void updateData(String classValue, String age, int id, String number,String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("class", classValue);
        values.put("age", age);
        values.put("number", number);
        values.put("name", name);

        database.update("myTable", values, "id = ?", new String[]{String.valueOf(id)});
        database.close();
    }

    public void Deletee(String id){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL("delete from myTable where id like "+id);

    }







}
