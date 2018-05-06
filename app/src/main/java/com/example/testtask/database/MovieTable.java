package com.example.testtask.database;

public class MovieTable {
    public static final String TABLE = "movies";

    public static class COLUMN {
        public static final String ID = "_id";
        public static final String TITLE = "title";
        public static final String IMAGE = "IMAGE";
        public static final String RATING = "RATING";
        public static final String YEAR = "IMAGE";
        public static final String GENRE = "IMAGE";
        public static final String BOOKMARK = "IMAGE";
    }

    public static final String CREATE_SCRIPT =
            String.format("CREATE TABLE %s ("
                            + "%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + "%s TEXT,"
                            + "%s BLOB,"
                            + "%s REAL,"
                            + "%s INTEGER,"
                            + "%s TEXT,"
                            + "%s INTEGER DEFAULT 0," + ");",
                    TABLE, COLUMN.ID, COLUMN.TITLE, COLUMN.IMAGE,
                    COLUMN.RATING, COLUMN.YEAR, COLUMN.GENRE, COLUMN.BOOKMARK);
}
