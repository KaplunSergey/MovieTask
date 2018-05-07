package com.example.testtask.data.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmModel {

    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("rating")
    private float rating;
    @SerializedName("releaseYear")
    private int releaseYear;
    @SerializedName("genre")
    private List<String> genre = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

}