package com.example.digitallibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "digital_library.db";

    public static final String USER_TABLE = "user_table";
    public static final String BOOK_TABLE = "book_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE + " (userId INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, phone TEXT)";

        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
    }

    public boolean insertUserData(String username, String password, String phone) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        cv.put("phone", phone);

        long result = myDB.insert(USER_TABLE, null, cv);

        if (result == 1) return false;
        return true;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE username = ?",
                new String[]{username});

        if (cursor.getCount() > 0) return true;
        return false;
    }

    public boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE username = ? AND password = ?",
                new String[]{username, password});

        if (cursor.getCount() > 0) return true;
        return false;
    }

    public Cursor readCurrentUser(String username) {
        SQLiteDatabase myDB = this.getReadableDatabase();

        Cursor cursor = null;
        if (myDB != null) {
            cursor = myDB.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE USER_TABLE.username = ?", new String[]{username});
        }
        return cursor;
    }

    public Boolean editProfile(String username, String phone, String bio) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", phone);
        contentValues.put("bio", bio);
        int result = myDB.update(USER_TABLE, contentValues, "username = ?", new String[]{username});

        if (result > 0) return true;
        return false;
    }

    public Boolean editPassword(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        int result = myDB.update(USER_TABLE, contentValues, "username = ?", new String[]{username});

        if (result > 0) return true;
        return false;
    }
}