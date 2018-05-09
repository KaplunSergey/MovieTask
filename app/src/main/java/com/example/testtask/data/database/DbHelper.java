package com.example.testtask.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.testtask.Utils.MovieUtils;
import com.example.testtask.Utils.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper implements DbHandler {

    private static final String NAME = "movie";
    private static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MovieTable.CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void addMovies(List<Movie> movies) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (Movie movie : movies) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MovieTable.COLUMN.TITLE, movie.getTitle());
            contentValues.put(MovieTable.COLUMN.IMAGE, movie.getImageUrl());
            contentValues.put(MovieTable.COLUMN.RATING, movie.getRating());
            contentValues.put(MovieTable.COLUMN.YEAR, movie.getYear());
            String genre = StringUtils.convertListToString(movie.getGenre());
            contentValues.put(MovieTable.COLUMN.GENRE, genre);

            db.insert(MovieTable.TABLE, null, contentValues);
        }

        db.close();
    }

    @Override
    public Movie getMovie(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(MovieTable.TABLE, MovieTable.COLUMN.ALL_KEYS, MovieTable.COLUMN.ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Movie movie = MovieUtils.getMovieByCursor(cursor);

        cursor.close();
        db.close();

        return movie;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        String selectQuery = "select * from " + MovieTable.TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Movie movie = MovieUtils.getMovieByCursor(cursor);
                movies.add(movie);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return movies;
    }

    @Override
    public boolean moviesDownloaded() {
        String countQuery = "select * from " + MovieTable.TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        boolean moviesDownloaded = cursor.getCount() > 0;

        cursor.close();
        return moviesDownloaded;
    }

    @Override
    public void updateMovieBookmark(long id, boolean bookmark) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieTable.COLUMN.BOOKMARK, bookmark? 1 : 0);

        db.update(MovieTable.TABLE, values, MovieTable.COLUMN.ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    @Override
    public void deleteMovies() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MovieTable.TABLE, null, null);
        db.close();
    }
}
