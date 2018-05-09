package com.example.testtask.Utils;

import android.database.Cursor;

import com.example.testtask.data.database.Movie;

import java.util.Comparator;
import java.util.List;

public class MovieUtils {
    public static Movie getMovieByCursor(Cursor cursor) {
        Movie movie = new Movie();
        movie.setId(cursor.getInt(0));
        movie.setTitle(cursor.getString(1));
        movie.setImageUrl(cursor.getString(2));
        movie.setRating(cursor.getInt(3));
        movie.setYear(cursor.getInt(4));
        List<String> genre = StringUtils.convertStringToList(cursor.getString(5));
        movie.setGenre(genre);
        movie.setBookmark(cursor.getInt(6) != 0);

        return movie;
    }

    public static final Comparator<Movie> COMPARE_BY_RATING = new Comparator<Movie>() {
        @Override
        public int compare(Movie m1, Movie m2) {
            return (int) (m1.getRating() - m2.getRating());
        }
    };

    public static final Comparator<Movie> COMPARE_BY_YEAR = new Comparator<Movie>() {
        @Override
        public int compare(Movie m1, Movie m2) {
            return m1.getYear() - m2.getYear();
        }
    };
}
