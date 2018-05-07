package com.example.testtask.data.database;

public class FilmTable {
    public static final String TABLE = "movies";

    public static class COLUMN {
        public static final String ID = "_id";
        public static final String TITLE = "title";
        public static final String IMAGE = "image";
        public static final String RATING = "rating";
        public static final String YEAR = "year";
        public static final String GENRE = "genre";
        public static final String BOOKMARK = "bookmark";

        public static final String[] ALL_KEYS = new String[]{ID, TITLE, IMAGE, RATING, YEAR, GENRE, BOOKMARK};
    }

    public static final String CREATE_SCRIPT =
            String.format("create table %s ("
                            + "%s integer primary key autoincrement,"
                            + "%s text,"
                            + "%s blob,"
                            + "%s real,"
                            + "%s integer,"
                            + "%s text,"
                            + "%s integer default 0," + ");",
                    TABLE, COLUMN.ID, COLUMN.TITLE, COLUMN.IMAGE,
                    COLUMN.RATING, COLUMN.YEAR, COLUMN.GENRE, COLUMN.BOOKMARK);
}