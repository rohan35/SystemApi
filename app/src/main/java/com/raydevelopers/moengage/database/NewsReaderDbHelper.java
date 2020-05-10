package com.raydevelopers.moengage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.raydevelopers.moengage.database.NewsReaderContract.NewsEntry;

public class NewsReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NewsReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NewsEntry.TABLE_NAME + " (" +
                    NewsEntry._ID + " INTEGER PRIMARY KEY," +
                    NewsEntry.COLUMN_NAME_AUTHOR + " TEXT," +
                    NewsEntry.COLUMN_NAME_TITLE + " TEXT," +
                    NewsEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    NewsEntry.COLUMN_NAME_URL + " TEXT," +
                    NewsEntry.COLUMN_NAME_URL_TO_IMAGE + " TEXT," +
                    NewsEntry.COLUMN_NAME_PUBLISHED_AT + " TEXT," +
                    NewsEntry.COLUMN_NAME_CONTENT + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NewsEntry.TABLE_NAME;

    public NewsReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
