package com.nicotimeout.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "db_user.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "tb_user";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_QUIT_DATE = "quit_date";
    public static final String COLUMN_CIG_PER_DAY = "cig_per_day";
    public static final String COLUMN_CIG_PRICE = "cig_price";
    public static final String COLUMN_CIG_YEARS = "cig_years";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUIT_DATE + " INTEGER, " +
                COLUMN_CIG_PER_DAY + " INTEGER, " +
                COLUMN_CIG_PRICE + " INTEGER, " +
                COLUMN_CIG_YEARS + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void getUserData(int quit_date, int cig_per_day, int cig_price, int cig_years) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_QUIT_DATE, quit_date);
        cv.put(COLUMN_CIG_PER_DAY, cig_per_day);
        cv.put(COLUMN_CIG_PRICE, cig_price);
        cv.put(COLUMN_CIG_YEARS, cig_years);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Added Successfuly!",Toast.LENGTH_SHORT).show();

        }
    }
}

