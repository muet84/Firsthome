package com.example.mibrahim.firsthome;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by M.Ibrahim on 1/8/2017.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "fyp";
    public static String TABLE_NAME = "tbl_categories";
    Context context;
    public static int DB_VERSION = 1;
    public static String cat_id = "cat_id";
    public static String cat_name = "cat_name";
    public static String img_url = "img_url";
    public static String parent_id = "parent_id";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query_create_tbl_categories = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(cat_id INTEGER(11) UNIQUE PRIMARY KEY,cat_name VARCHAR(255),image_url VARCHAR(255),parent_id VARCHAR(255));";

        SQLiteDatabase db_obj = db;

        try {
            Toast.makeText(context, "DataBase Created", Toast.LENGTH_SHORT).show();
            db.execSQL(Query_create_tbl_categories);
            Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "DB Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

//            Log.i("DBError",e.getMessage() + "\n" + Query_Create_DB + "\n" +Query_create_tbl_categories);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertCategory(String cat_name, String img_url, String parent_id) {

        String Query_Insert_Category = "Insert into " + TABLE_NAME + "(cat_name, image_url, parent_id)values(\'" + cat_name + "\'," +
                "'http://192.168.1.8/fypmeetup/images/img_tech.jpeg', " + parent_id + ");";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(Query_Insert_Category);
            Toast.makeText(context, "Data Inserted", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Insert Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("DBError", e.getMessage() + "\n" + Query_Insert_Category);
        }
    }

    public void ShowCategries() {

        String Query_Get_Categories = "SELECT cat_name FROM " + TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query_Get_Categories, null);
        if (cursor.moveToFirst()) {
            do {
                String cat_name = cursor.getString(0);
                Log.i("CAT_NAME", cat_name);
                Toast.makeText(context, "cat_name" + cat_name, Toast.LENGTH_SHORT).show();
            }
            while (cursor.moveToNext());
        }

    }


}
