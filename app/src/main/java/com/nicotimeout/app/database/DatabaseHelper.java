package com.nicotimeout.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nicotimeout.app.user.thirdFragment;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE = "user_table";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_QUIT_DATE = "quit_date";
    public static final String COLUMN_CIG_PER_DAY = "cig_per_day";
    public static final String COLUMN_CIG_PRICE = "cig_price";
    public static final String COLUMN_CIG_YEARS = "cig_years";

    public DatabaseHelper(@Nullable Context context) {

        super(context, "user.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUIT_DATE + " STRING, " +
                COLUMN_CIG_PER_DAY + " INTEGER, " +
                COLUMN_CIG_PRICE + " INTEGER, " +
                COLUMN_CIG_YEARS + " INTEGER)";
        db.execSQL(createTableStatement);
    }

    public boolean addOne(UserModel userModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_QUIT_DATE, userModel.getQuit_date());
        cv.put(COLUMN_CIG_PER_DAY, userModel.getCig_per_day());
        cv.put(COLUMN_CIG_PRICE, userModel.getCig_price());
        cv.put(COLUMN_CIG_YEARS, userModel.getCig_years());

        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1) {
            return false;

        } else {
            return true;
        }

    }

    public Cursor getData() {
        String queryString = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    public Cursor getDataDate() {
        String queryString = "SELECT quit_date FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTableStatement = ("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL(deleteTableStatement);
        onCreate(db);
    }
}
