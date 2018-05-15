package com.example.testtask.ui.detailView.presenter;

import com.example.testtask.data.base.Movie;
import com.example.testtask.data.base.Repository;
import com.example.testtask.data.base.callback.MovieDownloadListener;
import com.example.testtask.data.base.exception.RepositoryException;
import com.example.testtask.ui.detailView.view.MovieDetailView;

public class MovieDetailPresenterImpl implements MovieDetailPresenter {

    private Repository repository;
    private MovieDetailView view;
    private Movie movie;

    public MovieDetailPresenterImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void attachView(MovieDetailView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void detailViewIsReady(int movieId) {

        repository.getMovie(movieId, new MovieDownloadListener() {
            @Override
            public void movieDownloaded(Movie model) {
                movie = model;
                view.showMovie(movie);
            }

            @Override
            public void error(RepositoryException ex) {
                view.showMessage(ex.getMessage());
            }
        });
    }

    @Override
    public void bookmarkButtonClicked() {
        movie.setBookmark(!movie.isBookmark());
        repository.updateMovieBookmark(movie);
        view.updateBookmarkButton(movie.isBookmark());
    }
}
