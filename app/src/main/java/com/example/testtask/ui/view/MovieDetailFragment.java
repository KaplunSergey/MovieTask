package com.example.testtask.ui.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testtask.App;
import com.example.testtask.R;
import com.example.testtask.Utils.StringUtils;
import com.example.testtask.data.database.Movie;
import com.example.testtask.ui.presenter.MovieDetailPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class MovieDetailFragment extends Fragment implements MovieDetailView {

    private static final String UN_BOOKMARK = "UnBookmark";
    private static final String BOOKMARK = "Bookmark";
    private static final String MOVIE_ID = "movie id";

    @Inject
    MovieDetailPresenter movieDetailPresenter;
    private ImageView movieImage;
    private TextView movieTitle;
    private TextView movieRating;
    private TextView movieYear;
    private TextView movieGenre;
    private Button movieBookmarkButton;
    private int movieId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieId = getActivity().getIntent().getIntExtra(MOVIE_ID, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(R.string.movie_detail_fragment_title);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.movie_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        movieDetailPresenter.attachView(this);

        movieImage = view.findViewById(R.id.movie_detail_image);
        movieTitle = view.findViewById(R.id.movie_detail_title);
        movieRating = view.findViewById(R.id.movie_detail_rating);
        movieYear = view.findViewById(R.id.movie_detail_year);
        movieGenre = view.findViewById(R.id.movie_detail_genre);
        movieBookmarkButton = view.findViewById(R.id.bookmark_button);

        movieDetailPresenter.setMovieId(movieId);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                movieDetailPresenter.onBack();
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        movieDetailPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void updateBookmarkButton(boolean bookmark) {
        changeBookmarkButtonText(bookmark);
    }

    @Override
    public void showMovie(final Movie movie) {
        Picasso.get().load(movie.getImageUrl()).fit().into(movieImage);
        movieTitle.setText(movie.getTitle());
        movieRating.setText(String.valueOf(movie.getRating()));
        movieYear.setText(String.valueOf(movie.getYear()));
        movieGenre.setText(StringUtils.convertListToString(movie.getGenre()));
        changeBookmarkButtonText(movie.isBookmark());

        movieBookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieDetailPresenter.bookmarkButtonClicked();
            }
        });
    }

    private void changeBookmarkButtonText(boolean bookmark) {
        if (bookmark) {
            movieBookmarkButton.setText(UN_BOOKMARK);
        } else {
            movieBookmarkButton.setText(BOOKMARK);
        }
    }
}
