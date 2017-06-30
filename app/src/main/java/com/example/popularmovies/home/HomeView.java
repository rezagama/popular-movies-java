package com.example.popularmovies.home;

import com.example.popularmovies.home.model.Movie;

/**
 * Created by rezagama on 6/30/17.
 */

public interface HomeView {
    void onViewCreated();

    void onLoadMovieGrid(Movie movie);

    void onError();
}
