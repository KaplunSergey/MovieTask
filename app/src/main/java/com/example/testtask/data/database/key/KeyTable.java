package com.example.testtask.data.database.key;

public interface KeyTable {
    String TABLE = "keys";

    interface COLUMN {
        String ID = "_id";
        String PUBLIC_KEY = "public_key";
        String PRIVATE_KEY = "private_key";
    }

    String CREATE_SCRIPT =
            String.format("create table %s ("
                            + "%s integer primary key autoincrement,"
                            + "%s text,"
                            + "%s text" + ");",
                    TABLE, COLUMN.ID, COLUMN.PUBLIC_KEY, COLUMN.PRIVATE_KEY);

    String INSERT_KEY = "insert into " + TABLE + " (" + COLUMN.PUBLIC_KEY +
            "," + COLUMN.PRIVATE_KEY + ") " + "values(?,?)";

    String SELECT_KEY = "select * from " + TABLE + " where " + COLUMN.ID + " = ?";

    String UPDATE_KEY_BY_ID = "update " + TABLE + " set " +
            COLUMN.PUBLIC_KEY + " = ?" + " , " + COLUMN.PRIVATE_KEY + " = ?" + " where " + COLUMN.ID + " = ?";

    String DELETE_KEY = "delete from " + TABLE + " where " + COLUMN.ID + " = ?";
}
