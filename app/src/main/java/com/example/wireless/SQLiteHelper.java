package com.example.wireless;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
// This clss helps to connect and deal with db
public class SQLiteHelper extends SQLiteOpenHelper {

        private static SQLiteHelper sqLiteDB;
        private static final String DB_NAME = "dbases";
        private static final int DB_VERSION = 1;

        // using constructor via context, database name, and version
        private SQLiteHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
        // Singleton for only 1 instance to sync db
        public static synchronized SQLiteHelper getInstance(Context context) {
            if(sqLiteDB == null) {
                sqLiteDB = new SQLiteHelper(context.getApplicationContext());
            }
            return sqLiteDB;
        }
        // Create table
        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql =
                    "CREATE TABLE IF NOT EXISTS important_day (" +
                            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "date INTEGER, " +
                            "month INTEGER, "  +
                            "day_name TEXT)";

            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {

        }

    }


