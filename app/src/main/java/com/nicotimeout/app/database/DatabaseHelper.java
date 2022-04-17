package com.nicotimeout.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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



    /*  public ArrayList<UserModel> getEveryone() {
          // on below line we are creating a new array list.
          ArrayList<UserModel> returnList = new ArrayList<>();

          // on below line we are creating a
          // database for reading our database.
          SQLiteDatabase db = this.getReadableDatabase();

          //query for getting all data
          String queryString = "SELECT * FROM " + USER_TABLE;
          // on below line we are creating a cursor with query to read data from database.
          Cursor cursor = db.rawQuery(queryString, null);



          // moving our cursor to first position.
          if (cursor.moveToFirst()) {
              do {
                  // on below line we are adding the data from cursor to our array list.
                  //(int user_id, String quit_date, int cig_per_day, int cig_price, int cig_years)
                  int user_id = cursor.getInt(0);
                  String quit_date = cursor.getString(1);
                  int cig_per_day = cursor.getInt(2);
                  int cig_price = cursor.getInt(3);
                  int cig_years = cursor.getInt(4);

              } while (cursor.moveToNext());
          } else {

          }
          // at last closing our cursor
          // and returning our array list.
          cursor.close();
  //        db.close();
          return returnList;
      }*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTableStatement = ("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL(deleteTableStatement);
        onCreate(db);
    }
}

//    public List<UserModel> getEveryone() {
//        List<UserModel> returnList = new ArrayList<>();
//
//        //get data from the database
//
//        String queryString = " SELECT * FROM " + USER_TABLE;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(queryString, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int USERID = cursor.getInt(0);
//                String QUIT_DATE = cursor.getString(1);
//                int CIG_PER_DAY = cursor.getInt(2);
//                int CIG_PRICE = cursor.getInt(3);
//                int CIG_YEARS = cursor.getInt(4);
//
//                UserModel newUserModel = new UserModel(
//                        USERID,QUIT_DATE, CIG_PER_DAY, CIG_PRICE, CIG_YEARS);
//                returnList.add(newUserModel);
//            } while (cursor.moveToFirst());
//
//        } else {
//            //failure doesn't do anything
//        }
//
//        //closing what need to be closed haha
//        cursor.close();
//        db.close();
//        return returnList;
//
//    }
//}
