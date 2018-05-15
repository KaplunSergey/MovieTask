package com.example.testtask.ui.mainView.view;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
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
import com.example.testtask.ui.mainView.callback.ClickMovieListener;
import com.example.testtask.common.Constant;
import com.example.testtask.data.base.Movie;
import com.example.testtask.ui.detailView.view.MovieDetailActivity;
import com.example.testtask.ui.mainView.presenter.MoviesPresenter;

import java.util.List;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

public class MoviesFragment extends Fragment implements MoviesView {

    private static final int REQUEST_CODE = 1;
    private static final float APPLY_ALPHA = 0.2f;
    private static final float CANCEL_ALPHA = 1.0f;

    @Inject
    MoviesPresenter moviesPresenter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MovieAdapter movieAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieAdapter = new MovieAdapter(movieListener);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(R.string.movies_fragment_title);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycle_view);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(movieAdapter);

        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(R.string.movies_fragment_title);
        }
        moviesPresenter.attachView(this);
        moviesPresenter.viewIsReady();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        return inflater.inflate(R.layout.movies_fragment, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.synchronize_button:
                moviesPresenter.updateMovies();
                break;
            case R.id.rating_up:
                moviesPresenter.sortMoviesRatingUp();
            break;
            case R.id.rating_down:
                moviesPresenter.sortMoviesRatingDown();
                break;
            case R.id.year_up:
                moviesPresenter.sortMoviesYearUp();
                break;
            case R.id.year_down:
                moviesPresenter.sortMoviesYearDown();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        movieAdapter.setMovies(movies);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startProgress() {
        recyclerView.setAlpha(APPLY_ALPHA);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgress() {
        recyclerView.setAlpha(CANCEL_ALPHA);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void openMovie(int movieId) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(Constant.MOVIE_ID_FIELD, movieId);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != REQUEST_CODE || resultCode != RESULT_OK) {
            return;
        }

        int movie_id = data.getIntExtra(Constant.MOVIE_ID_FIELD, 0);
        moviesPresenter.changeMovieElement(movie_id);
    }

    @Override
    public void updateMovieElement(Movie movie) {
        movieAdapter.itemChanged(movie);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        moviesPresenter.detachView();
    }

    private ClickMovieListener movieListener = new ClickMovieListener() {
        @Override
        public void movieClicked(int id) {
            moviesPresenter.selectedMovie(id);
        }
    };
}
