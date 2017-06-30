package com.example.popularmovies.network;

import com.example.popularmovies.home.model.Movie;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by rezagama on 6/30/17.
 */

public interface NetworkService {
    @GET("movie/popular")
    Observable<Movie> getMovieList();
}
