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
import com.example.testtask.common.Constant;
import com.example.testtask.data.database.Movie;
import com.example.testtask.ui.presenter.MoviesPresenter;

import java.util.List;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

public class MoviesFragment extends Fragment implements MoviesView {

    private static final int REQUEST_CODE = 1;

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
        moviesPresenter.attachView(this);
        moviesPresenter.viewIsReady();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.movies_fragment_title);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.movies_fragment, container, false);
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
    public void updateMovieElement(Movie movie, int position) {
        movieAdapter.itemChanged(movie, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        moviesPresenter.detachView();
    }

    private ClickMovieListener movieListener = new ClickMovieListener() {
        @Override
        public void movieClicked(int id, int position) {
            moviesPresenter.selectedMovie(id, position);
        }
    };
}
