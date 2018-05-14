package com.example.testtask.data.database.movie;

public interface MovieTable {
    String TABLE = "movies";

    interface COLUMN {
        String ID = "_id";
        String TITLE = "title";
        String IMAGE = "image";
        String RATING = "rating";
        String YEAR = "year";
        String GENRE = "genre";
        String BOOKMARK = "bookmark";
    }

    String CREATE_SCRIPT =
            String.format("create table %s ("
                            + "%s integer primary key autoincrement,"
                            + "%s text,"
                            + "%s text,"
                            + "%s real,"
                            + "%s integer,"
                            + "%s text,"
                            + "%s integer default 0" + ");",
                    TABLE, COLUMN.ID, COLUMN.TITLE, COLUMN.IMAGE,
                    COLUMN.RATING, COLUMN.YEAR, COLUMN.GENRE, COLUMN.BOOKMARK);

    String INSERT_MOVIE = "insert into " + MovieTable.TABLE + " (" + MovieTable.COLUMN.TITLE +
            "," + MovieTable.COLUMN.IMAGE + "," + MovieTable.COLUMN.RATING + "," +
            MovieTable.COLUMN.YEAR + "," + MovieTable.COLUMN.GENRE + ") " + "values(?,?,?,?,?)";

    String SELECT_MOVIE = "select * from " + MovieTable.TABLE + " where " + MovieTable.COLUMN.ID + " = ?";

    String SELECT_MOVIES = "select * from " + MovieTable.TABLE;

    String SELECT_MOVIES_COUNT = "select count(*) from " + MovieTable.TABLE;

    String UPDATE_MOVIE_BOOKMARK_BY_ID = "update " + MovieTable.TABLE + " set " +
            MovieTable.COLUMN.BOOKMARK + " = ?" + " where " + MovieTable.COLUMN.ID + " = ?";

    String DELETE_MOVIE_TABLE = "delete from " + MovieTable.TABLE;
}
