package com.example.testtask.ui.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import com.example.testtask.ui.presenter.MoviesPresenter;

import java.util.List;

import javax.inject.Inject;

public class MoviesFragment extends Fragment implements MoviesView {

    @Inject
    MoviesPresenter moviesPresenter;

    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getActivity().findViewById(R.id.recycle_view);
        progressBar = getActivity().findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        moviesPresenter.attachView(this);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.movies_fragment_title);
        moviesPresenter.viewIsReady();
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
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies, new ClickMovieListener() {
            @Override
            public void clickMovie(int id) {
                moviesPresenter.selectedMovie(id);
            }
        });
        recyclerView.setAdapter(moviesAdapter);
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
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setMovieId(movieId);
        fragmentTransaction.replace(R.id.fragment_container, movieDetailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
