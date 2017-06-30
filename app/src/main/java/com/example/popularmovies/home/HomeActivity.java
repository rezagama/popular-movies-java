package com.example.popularmovies.home;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ActivityHomeBinding;
import com.example.popularmovies.deps.AppDependenciesProvider;
import com.example.popularmovies.home.adapter.MovieGridAdapter;
import com.example.popularmovies.home.model.Movie;
import com.example.popularmovies.home.model.Result;
import com.example.popularmovies.services.MovieService;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeView {
    private static final int GRID_COLUMN = 2;

    private MovieGridAdapter adapter;
    private HomePresenter presenter;
    private ActivityHomeBinding binding;

    @Inject
    MovieService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppDependenciesProvider) getApplicationContext()).provideAppDependencies().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        adapter = new MovieGridAdapter(this::navigateToMovieDetail);
        presenter = new HomePresenter(service, this);
        presenter.onViewCreated();
    }


    @Override
    public void onViewCreated() {
        binding.listMovieGrid.setLayoutManager(new GridLayoutManager(this, GRID_COLUMN));
        binding.listMovieGrid.setAdapter(adapter);
        presenter.getMovieList();
    }

    private void navigateToMovieDetail(Result result){
        //TODO: Handle movie detail navigation
    }

    @Override
    public void onLoadMovieGrid(Movie movie) {
        adapter.setResults(movie.results);
    }

    @Override
    public void onError() {

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
