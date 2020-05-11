package com.raydevelopers.moengage.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewsDbOperationHelper {
    private NewsReaderDbHelper mNewsReaderDbHelper;
    public static final int NEWS_CODE = 1;
    // uri matcher to match the incoming uri with our uri
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        String content = NewsReaderContract.CONTENT_AUTHORITY;
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(content, NewsReaderContract.PATH, NEWS_CODE);
        return matcher;

    }

    // passing the object of news helper
    public NewsDbOperationHelper(NewsReaderDbHelper newsReaderDbHelper) {
        this.mNewsReaderDbHelper = newsReaderDbHelper;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case NEWS_CODE:
                // reading the data from the database using helper object
                cursor = mNewsReaderDbHelper.getReadableDatabase().query(
                        NewsReaderContract.NewsEntry.TABLE_NAME,// The table to query
                        projection,// The array of columns to return
                        selection,// The columns for the WHERE clause
                        selectionArgs,// The values for the WHERE clause
                        null,// don't group the rows
                        null,// don't filter by row groups
                        sortOrder);// The sort order
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return cursor;
    }

    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mNewsReaderDbHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)) {

            case NEWS_CODE: {
                // writing the data from the database using helper object. we will be using
                // bulk insert method to add data if its more that 1 item
                db.beginTransaction();
                int rowsInserted = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(NewsReaderContract.NewsEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            rowsInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                return rowsInserted;

            }
            default:
                return -1;
        }
    }

    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int numRowsDeleted;
        // if selection is null put the 1 value in it
        if (null == selection) selection = "1";

        switch (sUriMatcher.match(uri)) {

            case NEWS_CODE:
                // if URi matches query the table and delete the item
                numRowsDeleted = mNewsReaderDbHelper.getWritableDatabase().delete(
                        NewsReaderContract.NewsEntry.TABLE_NAME,
                        selection,
                        selectionArgs);

                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return numRowsDeleted;
    }

}
