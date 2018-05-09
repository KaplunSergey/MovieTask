package com.example.testtask.ui.view;

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
import com.example.testtask.callback.ClickMovieListener;
import com.example.testtask.data.database.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{

    private List<Movie> movies;
    private ClickMovieListener clickMovieListener;

    MoviesAdapter(List<Movie> movies, ClickMovieListener clickMovieListener) {
        this.movies = movies;
        this.clickMovieListener = clickMovieListener;
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
        Picasso.get().load(movies.get(position).getImageUrl()).fit().into(holder.movieImage);
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.movieBookmark.setChecked(movies.get(position).isBookmark());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMovieListener.clickMovie(movies.get(position).getId());
            }
        });
        holder.movieBookmark.setChecked(movies.get(position).isBookmark());
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
    }
}
