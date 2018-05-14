package com.example.testtask.data.database.movie;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.testtask.Utils.MovieUtils;
import com.example.testtask.Utils.StringUtils;
import com.example.testtask.data.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {

    private SQLiteDatabase db;

    public MovieDAOImpl(DbHelper dbHelper) {
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void addMovies(List<MovieDb> movies) {
        db.beginTransaction();

        try {
            for (MovieDb movieDb : movies) {

                SQLiteStatement statement = db.compileStatement(MovieTable.INSERT_MOVIE);
                statement.bindString(1, movieDb.getTitle());
                statement.bindString(2, movieDb.getImageUrl());
                statement.bindDouble(3, movieDb.getRating());
                statement.bindLong(4, movieDb.getYear());
                statement.bindString(5, StringUtils.convertListToString(movieDb.getGenre()));

                statement.execute();
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public MovieDb getMovie(long id) {
        Cursor cursor = db.rawQuery(MovieTable.SELECT_MOVIE, new String[]{String.valueOf(id)});

        if (cursor == null || !cursor.moveToFirst()) {
            return null;
        }

        MovieDb movieDb = MovieUtils.getMovieByCursor(cursor);
        cursor.close();

        return movieDb;
    }

    @Override
    public List<MovieDb> getMovies() {
        List<MovieDb> movies = new ArrayList<>();
        Cursor cursor = db.rawQuery(MovieTable.SELECT_MOVIES, null);

        if (cursor == null || !cursor.moveToFirst()) {
            return null;
        }

        do {
            MovieDb movieDb = MovieUtils.getMovieByCursor(cursor);
            movies.add(movieDb);
        } while (cursor.moveToNext());
        cursor.close();

        return movies;
    }

    @Override
    public boolean moviesDownloaded() {

        /**
         * Cursor extends AutoCloseable
         * that is why you can use (try with resources) and don't mind about close cursor.
         * it will be closed automatically
         */

        try(Cursor cursor = db.rawQuery(MovieTable.SELECT_MOVIES_COUNT, null)) {
            if (cursor == null || !cursor.moveToFirst()) {
                return false;
            }
            int rawCount = cursor.getInt(0);
            return rawCount > 0;
        }
    }

    @Override
    public void updateMovieBookmark(long id, boolean bookmark) {
        int bookmarkValue = bookmark ? 1 : 0;
        db.execSQL(MovieTable.UPDATE_MOVIE_BOOKMARK_BY_ID,
                new String[]{String.valueOf(bookmarkValue), String.valueOf(id)});
    }

    @Override
    public void deleteMovies() {
        db.execSQL(MovieTable.DELETE_MOVIE_TABLE);
    }

    @Override
    public void closeDb() {
        db.close();
    }
}
