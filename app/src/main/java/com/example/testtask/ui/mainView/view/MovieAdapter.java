package com.example.testtask.ui.mainView.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testtask.R;
import com.example.testtask.Utils.MovieUtils;
import com.example.testtask.ui.mainView.callback.ClickMovieListener;
import com.example.testtask.data.base.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviesViewHolder> {

    private List<Movie> movies;
    private ClickMovieListener clickMovieListener;

    MovieAdapter(ClickMovieListener clickMovieListener) {
        this.clickMovieListener = clickMovieListener;
        movies = new ArrayList<>();
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, final int position) {
        holder.bind(movies.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMovieListener.movieClicked(movies.get(position).getId());
            }
        });
    }

    public void itemChanged(Movie model) {
        int index = movies.indexOf(model);

        if (index == -1) {
            return;
        }

        movies.set(index, model);
        notifyItemChanged(index);
    }

    public void setMovies(List<Movie> movies) {
        this.movies.clear();

        if (movies != null) {
            this.movies.addAll(movies);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView movieImage;
        TextView movieTitle;
        CheckBox movieBookmark;

        MoviesViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            movieImage = itemView.findViewById(R.id.movie_image);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieBookmark = itemView.findViewById(R.id.movie_bookmark);
        }

        void bind(Movie movie) {
            Picasso.get().load(movie.getImageUrl()).fit().into(movieImage);
            movieTitle.setText(movie.getTitle());
            movieBookmark.setChecked(movie.isBookmark());
        }
    }
}
