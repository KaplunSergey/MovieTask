package com.example.testtask.ui.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testtask.App;
import com.example.testtask.R;
import com.example.testtask.Utils.StringUtils;
import com.example.testtask.common.Constant;
import com.example.testtask.data.database.Movie;
import com.example.testtask.ui.presenter.MovieDetailPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    private static final String UN_BOOKMARK = "UnBookmark";
    private static final String BOOKMARK = "Bookmark";

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);

        movieId = getIntent().getIntExtra(Constant.MOVIE_ID_FIELD, 0);

        App.getAppComponent().inject(this);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(R.string.movie_detail_fragment_title);

        movieDetailPresenter.attachView(this);

        movieImage = findViewById(R.id.movie_detail_image);
        movieTitle = findViewById(R.id.movie_detail_title);
        movieRating = findViewById(R.id.movie_detail_rating);
        movieYear = findViewById(R.id.movie_detail_year);
        movieGenre = findViewById(R.id.movie_detail_genre);
        movieBookmarkButton = findViewById(R.id.bookmark_button);

        movieDetailPresenter.setMovieId(movieId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        movieDetailPresenter.onBackPressed();
        setResult(RESULT_OK, getIntent());
        finish();
    }

    @Override
    public void updateBookmarkButton(boolean bookmark) {
        changeBookmarkButtonText(bookmark);
    }

    @Override
    public void showMovie(Movie movie) {
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
