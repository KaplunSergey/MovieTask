package com.example.testtask.Utils;

import android.database.Cursor;

import com.example.testtask.data.base.Movie;
import com.example.testtask.data.database.movie.MovieDb;
import com.example.testtask.data.network.movie.MovieNet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovieUtils {
    public static MovieDb getMovieByCursor(Cursor cursor) {
        MovieDb movieDb = new MovieDb();
        movieDb.setId(cursor.getInt(0));
        movieDb.setTitle(cursor.getString(1));
        movieDb.setImageUrl(cursor.getString(2));
        movieDb.setRating(cursor.getInt(3));
        movieDb.setYear(cursor.getInt(4));
        List<String> genre = StringUtils.convertStringToList(cursor.getString(5));
        movieDb.setGenre(genre);
        movieDb.setBookmark(cursor.getInt(6) != 0);

        return movieDb;
    }

    public static List<MovieDb> getMovieDbByMovieNetList(List<MovieNet> movies) {
        List<MovieDb> models = new ArrayList<>();

        for (MovieNet movieNet : movies) {
            MovieDb movieDb = new MovieDb();
            movieDb.setTitle(movieNet.getTitle());
            movieDb.setImageUrl(movieNet.getImage());
            movieDb.setRating(movieNet.getRating());
            movieDb.setYear(movieNet.getReleaseYear());
            movieDb.setGenre(movieNet.getGenre());
            models.add(movieDb);
        }

        return models;
    }

    public static List<Movie> getMoviesByMovieDbList(List<MovieDb> moviesDb) {
        List<Movie> movies = new ArrayList<>();

        for (MovieDb model : moviesDb) {
            movies.add(getMovieByMovieDb(model));
        }

        return movies;
    }

    public static Movie getMovieByMovieDb(MovieDb movieDb) {
        Movie movie = new Movie();
        movie.setId(movieDb.getId());
        movie.setGenre(movieDb.getGenre());
        movie.setImageUrl(movieDb.getImageUrl());
        movie.setRating(movieDb.getRating());
        movie.setTitle(movieDb.getTitle());
        movie.setYear(movieDb.getYear());
        movie.setBookmark(movieDb.isBookmark());

        return movie;
    }

    public static final Comparator<Movie> COMPARE_BY_RATING = new Comparator<Movie>() {
        @Override
        public int compare(Movie m1, Movie m2) {
            return Float.compare(m1.getRating(), m2.getRating());
        }
    };

    public static final Comparator<Movie> COMPARE_BY_YEAR = new Comparator<Movie>() {
        @Override
        public int compare(Movie m1, Movie m2) {
            return Integer.compare(m1.getYear(), m2.getYear());
        }
    };
}
