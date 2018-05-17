package com.example.testtask.data.database.key;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.testtask.data.database.DbHelper;

public class KeyDAOImpl implements KeyDAO {

    private SQLiteDatabase db;

    public KeyDAOImpl(DbHelper dbHelper) {
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void addKey(KeyDb keyDb) {
        SQLiteStatement statement = db.compileStatement(KeyTable.INSERT_KEY);
        statement.bindString(1, keyDb.getPublicKey());
        statement.bindString(2, keyDb.getPrivateKey());
        statement.execute();
    }

    @Override
    public KeyDb getKey() {
        try(Cursor cursor = db.rawQuery(KeyTable.SELECT_KEY, null)) {

            if (cursor == null || !cursor.moveToFirst()) {
                return null;
            }

            KeyDb keyDb = new KeyDb();
            keyDb.setId(cursor.getInt(0));
            keyDb.setPublicKey(cursor.getString(1));
            keyDb.setPrivateKey(cursor.getString(2));

            return keyDb;
        }
    }

    @Override
    public void updateKey(KeyDb keyDb) {
        db.execSQL(KeyTable.UPDATE_KEY_BY_ID,
                new String[]{keyDb.getPublicKey(), keyDb.getPrivateKey(), String.valueOf(keyDb.getId())});
    }

    @Override
    public void deleteKey(KeyDb keyDb) {
        db.execSQL(KeyTable.DELETE_KEY, new String[]{String.valueOf(keyDb.getId())});
    }

    @Override
    public void close() {
        db.close();
    }
}
