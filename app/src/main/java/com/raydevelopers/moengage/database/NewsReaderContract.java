package com.raydevelopers.moengage.database;

import android.net.Uri;
import android.provider.BaseColumns;

public final class NewsReaderContract
{
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private NewsReaderContract() {}

    public static final String CONTENT_AUTHORITY = NewsReaderContract.class.getPackage().toString();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH = "news";
    /* Inner class that defines the table contents */
    public static class NewsEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH)
                .build();
        public static final String TABLE_NAME = "newsEntry";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_URL_TO_IMAGE = "imageUrl";
        public static final String COLUMN_NAME_PUBLISHED_AT = "published_at";
        public static final String COLUMN_NAME_CONTENT = "content";
    }
}