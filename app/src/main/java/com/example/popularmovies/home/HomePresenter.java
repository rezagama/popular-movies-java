package com.example.popularmovies.home;

import com.example.popularmovies.home.model.Movie;
import com.example.popularmovies.network.NetworkCallback;
import com.example.popularmovies.services.MovieService;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by rezagama on 6/30/17.
 */

public class HomePresenter {
    private CompositeSubscription subscriptions;
    private MovieService service;
    private HomeView view;

    public HomePresenter(MovieService service, HomeView view) {
        this.service = service;
        this.view = view;
        subscriptions = new CompositeSubscription();
    }

    public void onViewCreated(){
        view.onViewCreated();
    }

        view.resetResults();
        view.showProgressText();
            @Override
            public void onSuccess(Movie response) {
                view.hideProgressText();
                view.onLoadMovieGrid(response);
            }

            @Override
            public void onError(Throwable error) {
                view.onError();
            }

            @Override
            public void onCompleted() {

            }
        });
        subscriptions.add(subscription);
    }

    public void onDestroy(){
        subscriptions.clear();
    }
}
