package com.example.alessander.movieapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.net.URL;
import java.util.HashMap;

public class MovieProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.example.alessander.provider.Movies";
    static final String URL = "content://" + PROVIDER_NAME + "/movies";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String NAME = "name";
    static final String OVERVIEW = "overview";
    static final String DATE = "date";
    static final String REVIEW = "review";
    static final String RATING = "rating";
    static final String YOUTUBE1 = "youtube1";
    static final String YOUTUBE2 = "youtube2";
    static final String TITLE = "title";

    private static HashMap<String, String> MOVIES_PROJECTION_MAP;

    static final int MOVIES = 1;
    static final int MOVIES_ID = 2;

    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "movies", MOVIES);
        uriMatcher.addURI(PROVIDER_NAME, "movies/#", MOVIES_ID);
    }

    private SQLiteDatabase db;

    static final String DATABASE_NAME = "Movies";
    static final String MOVIES_TABLE_NAME = "Movies";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + MOVIES_TABLE_NAME +
                    " (name, " +
                    " overview, " +
                    " title, " +
                    " review, " +
                    " rating, " +
                    " youtube1, " +
                    " youtube2, " +
                    " date);";

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
