package com.example.popularmovies.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezagama on 6/30/17.
 */

public class Result {
    @SerializedName("vote_count")
    public int voteCount;
    @SerializedName("id")
    public int id;
    @SerializedName("video")
    public boolean video;
    @SerializedName("vote_average")
    public float voteAverage;
    @SerializedName("title")
    public String title;
    @SerializedName("popularity")
    public float popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("genre_ids")
    public List<Integer> genreIds = new ArrayList<>();
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("adult")
    public boolean adult;
    @SerializedName("overview")
    public String overview;
    @SerializedName("release_date")
    public String releaseDate;
}
