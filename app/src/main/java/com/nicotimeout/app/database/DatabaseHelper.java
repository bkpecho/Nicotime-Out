package com.nicotimeout.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USERID = "USERID";
    public static final String COLUMN_CIG_PER_DAY = "CIG_PER_DAY";
    public static final String COLUMN_CIG_PRICE = "CIG_PRICE";
    public static final String COLUMN_CIG_YEARS = "CIG_YEARS";

    public DatabaseHelper(@Nullable Context context) {

        super(context, "user.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" +
                COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CIG_PER_DAY + " INTEGER, " +
                COLUMN_CIG_PRICE + " INTEGER, " +
                COLUMN_CIG_YEARS + " INTEGER)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(UserModel userModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

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
}
