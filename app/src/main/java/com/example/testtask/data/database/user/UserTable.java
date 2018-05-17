package com.example.testtask.data.database.user;

public interface UserTable {
    String TABLE = "users";

    interface COLUMN {
        String ID = "_id";
        String LOGIN = "login";
        String PASSWORD = "password";
    }

    String CREATE_SCRIPT =
            String.format("create table %s ("
                            + "%s integer primary key autoincrement,"
                            + "%s text,"
                            + "%s text" + ");",
                    TABLE, COLUMN.ID, COLUMN.LOGIN, COLUMN.PASSWORD);

    String INSERT_USER = "insert into " + TABLE + " (" + COLUMN.LOGIN +
            "," + COLUMN.PASSWORD + ") " + "values(?,?)";

    String SELECT_USER_BY_LOGIN = "select * from " + TABLE + " where " + COLUMN.LOGIN + " = ?";

    String UPDATE_USER_BY_ID = "update " + TABLE + " set " +
            COLUMN.LOGIN + " = ?" + " , " + COLUMN.PASSWORD + " = ?" + " where " + COLUMN.ID + " = ?";

    String DELETE_USER = "delete from " + TABLE + " where " + COLUMN.ID + " = ?";
}
