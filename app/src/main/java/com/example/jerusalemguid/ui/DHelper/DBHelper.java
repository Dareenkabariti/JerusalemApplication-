package com.example.jerusalemguid.ui.DHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Placedata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table PlaceDetails(name TEXT primary key, description TEXT, image TEXT, history TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
      DB.execSQL("drop Table if exists PlaceDetails");
    }
    public Boolean insertFavPlace(String name , String desc , String image ,String history ){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", desc);
        contentValues.put("image", image);
        contentValues.put("history", history);
        long result = DB.insert("PlaceDetails",null , contentValues);
        if (result==1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteData(String name )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PlaceDetails where name = ?", new String[]{name});
        if(cursor.getCount() > 0){
            long result = DB.delete("PlaceDetails", "name=?", new String[]{name});
            if(result == -1){
               return false ;
            }else{
                return true;
            }

        }else {
            return false ;
    }

    }

    public Cursor getData() {
      SQLiteDatabase DB  = this.getWritableDatabase();
      Cursor cursor = DB.rawQuery("Select * from PlaceDetails", null);
      return cursor;
    }
}
