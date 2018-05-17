package com.example.testtask.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.testtask.data.database.key.KeyTable;
import com.example.testtask.data.database.movie.MovieTable;
import com.example.testtask.data.database.user.UserTable;


public class DbHelper extends SQLiteOpenHelper {

    private static final String NAME = "movie";
    private static final int VERSION = 2;

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MovieTable.CREATE_SCRIPT);
        sqLiteDatabase.execSQL(KeyTable.CREATE_SCRIPT);
        sqLiteDatabase.execSQL(UserTable.CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < VERSION) {
            sqLiteDatabase.execSQL(KeyTable.CREATE_SCRIPT);
            sqLiteDatabase.execSQL(UserTable.CREATE_SCRIPT);
        }
    }
}
