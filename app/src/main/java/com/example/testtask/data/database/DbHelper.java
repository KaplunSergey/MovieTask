package com.example.testtask.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.testtask.Utils.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper implements DbHandler {

    public static final String NAME = "movie";
    public static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FilmTable.CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void addFilms(List<Film> films) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (Film film : films) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(FilmTable.COLUMN.TITLE, film.getTitle());
            contentValues.put(FilmTable.COLUMN.IMAGE, film.getImage());
            contentValues.put(FilmTable.COLUMN.RATING, film.getRating());
            contentValues.put(FilmTable.COLUMN.YEAR, film.getYear());
            String list = StringUtils.convertListToString(film.getGenre());
            contentValues.put(FilmTable.COLUMN.TITLE, list);

            db.insert(FilmTable.TABLE, null, contentValues);
        }

        db.close();
    }

    @Override
    public Film getFilm(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FilmTable.TABLE, FilmTable.COLUMN.ALL_KEYS, FilmTable.COLUMN.ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Film film = new Film();
        film.setId(cursor.getLong(0));
        film.setTitle(cursor.getString(1));
        film.setImage(cursor.getBlob(2));
        film.setRating(cursor.getInt(3));
        film.setYear(cursor.getInt(4));
        List<String> genre = StringUtils.convertStringToList(cursor.getString(5));
        film.setGenre(genre);
        film.setBookmark(cursor.getInt(6) != 0);

        cursor.close();
        db.close();

        return film;
    }

    @Override
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        String selectQuery = "select * from " + FilmTable.TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Film film = new Film();
                film.setId(cursor.getLong(0));
                film.setTitle(cursor.getString(1));
                film.setImage(cursor.getBlob(2));
                film.setRating(cursor.getInt(3));
                film.setYear(cursor.getInt(4));
                List<String> genre = StringUtils.convertStringToList(cursor.getString(5));
                film.setGenre(genre);
                film.setBookmark(cursor.getInt(6) != 0);
                films.add(film);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return films;
    }

    @Override
    public void updateBookmark(long id, boolean bookmark) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FilmTable.COLUMN.BOOKMARK, bookmark? 1 : 0);

        db.update(FilmTable.TABLE, values, FilmTable.COLUMN.ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
