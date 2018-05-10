package com.example.testtask.ui.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.testtask.App;
import com.example.testtask.R;
import com.example.testtask.callback.ClickMovieListener;
import com.example.testtask.data.database.Movie;
import com.example.testtask.ui.MovieDetailActivity;
import com.example.testtask.ui.presenter.MoviesPresenter;

import java.util.List;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

public class MoviesFragment extends Fragment implements MoviesView {

    private static final int REQUEST_CODE = 1;
    private static final String MOVIE_ID = "movie id";
    @Inject
    MoviesPresenter moviesPresenter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MovieAdapter movieAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycle_view);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.movies_fragment_title);
    }

    @Override
    public void onResume() {
        super.onResume();
        moviesPresenter.attachView(this);
        moviesPresenter.viewIsReady();
    }

    @Override
    public void onStop() {
        super.onStop();
        moviesPresenter.detachView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.synchronize_button:
                moviesPresenter.updateMovies();
                break;
            case R.id.rating_up:
                moviesPresenter.sortMovies(Sort.RATING_UP);
                break;
            case R.id.rating_down:
                moviesPresenter.sortMovies(Sort.RATING_DOWN);
                break;
            case R.id.year_up:
                moviesPresenter.sortMovies(Sort.YEAR_UP);
                break;
            case R.id.year_down:
                moviesPresenter.sortMovies(Sort.YEAR_DOWN);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.movies_fragment, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        movieAdapter = new MovieAdapter(movies, movieListener);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startProgress() {
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void openMovieDetailFragment(int movieId) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra("movie id", movieId);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != REQUEST_CODE) {
            return;
        }

        if (resultCode != RESULT_OK) {
            return;
        }

        int movie_id = data.getIntExtra(MOVIE_ID, 0);
        moviesPresenter.changeMovieElement(movie_id);
    }

    @Override
    public void updateMovieElement(Movie movie) {
        movieAdapter.notifyItemChanged(movie.getId());
    }

    private ClickMovieListener movieListener = new ClickMovieListener() {
        @Override
        public void clickMovie(int id) {
            moviesPresenter.selectedMovie(id);
        }
    };
}
