package com.example.testtask.data.database.user;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.testtask.data.database.DbHelper;

public class UserDAOImpl implements UserDAO{
    private SQLiteDatabase db;

    public UserDAOImpl(DbHelper dbHelper) {
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void addUser(UserDb userDb) {
        SQLiteStatement statement = db.compileStatement(UserTable.INSERT_USER);
        statement.bindString(1, userDb.getLogin());
        statement.bindString(2, userDb.getPassword());
        statement.execute();
    }

    @Override
    public UserDb getUser() {
        try(Cursor cursor = db.rawQuery(UserTable.SELECT_USER, null)) {

            if (cursor == null || !cursor.moveToFirst()) {
                return null;
            }

            UserDb userDb = new UserDb();
            userDb.setId(cursor.getInt(0));
            userDb.setLogin(cursor.getString(1));
            userDb.setPassword(cursor.getString(2));

            return userDb;
        }
    }

    @Override
    public void updateUser(UserDb userDb) {
        db.execSQL(UserTable.UPDATE_USER_BY_ID,
                new String[]{userDb.getLogin(), userDb.getPassword(), String.valueOf(userDb.getId())});
    }

    @Override
    public void deleteUser(UserDb userDb) {
        db.execSQL(UserTable.DELETE_USER, new String[]{String.valueOf(userDb.getId())});
    }

    @Override
    public void close() {
        db.close();
    }
}
