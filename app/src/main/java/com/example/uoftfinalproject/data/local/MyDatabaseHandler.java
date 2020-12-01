package com.example.uoftfinalproject.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHandler extends SQLiteOpenHelper {

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    private String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_USERS + "(" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null, " + COLUMN_EMAIL +
            " text not null, "  +
            COLUMN_PASSWORD + " text not null);";

    public MyDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if (oldVersion > 1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        //}
    }
    public void addUser(String name, String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(COLUMN_NAME,name);
        c.put(COLUMN_EMAIL,email);
        c.put(COLUMN_PASSWORD,password);
     db.insert(TABLE_USERS,null,c);
     db.close();


    }


    public boolean getLoginData(String email,String password)
    {
       SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID};

        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }

    }

