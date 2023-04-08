package com.example.programming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLData;

public class DataBase extends SQLiteOpenHelper {

     public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String qry1 = "create table users(Username text,Email text,Password text)";
         db.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void register(String Username,String Email,String Password){
        ContentValues cv = new ContentValues();
        cv.put("Username",Username);
        cv.put("Email",Email);
        cv.put("Password",Password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String Username,String Password){
         int result = 0;
         String str[] = new String[2];
         str[0] = Username;
         str[1] = Password;
         SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where Username=? and Password=?",str);
        if(c.moveToFirst()){
            result =1;
        }
        return result;
    }
}
