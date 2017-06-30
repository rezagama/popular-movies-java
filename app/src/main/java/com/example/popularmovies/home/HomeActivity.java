package com.example.popularmovies.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.AdapterView;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ActivityHomeBinding;
import com.example.popularmovies.deps.AppDependenciesProvider;
import com.example.popularmovies.detail.MovieDetailActivity;
import com.example.popularmovies.home.adapter.MovieGridAdapter;
import com.example.popularmovies.home.model.Movie;
import com.example.popularmovies.home.model.Result;
import com.example.popularmovies.services.MovieService;

import javax.inject.Inject;

import static com.example.popularmovies.detail.MovieDetailActivity.MOVIE_DATA;

public class HomeActivity extends AppCompatActivity implements HomeView {
    private static final int SORT_BY_POPULARITY = 0;
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
        binding.listMovieGrid.setAdapter(adapter);
        binding.listMovieGrid.setLayoutManager(new GridLayoutManager(this, GRID_COLUMN));
        binding.spinnerSortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == SORT_BY_POPULARITY){
                    presenter.getMovieList();
                } else {
                    presenter.getTopRatedMovies();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        presenter.getMovieList();
    }

    @Override
    public void resetResults(){
        adapter.clearResults();
        binding.setProgressTxt(getString(R.string.text_please_wait));
    }

    private void navigateToMovieDetail(Result result){
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MOVIE_DATA, result);
        startActivity(intent);
    }

    @Override
    public void onLoadMovieGrid(Movie movie) {
        adapter.setResults(movie.results);
    }

    @Override
    public void showProgressText() {
        binding.setProgressVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressText() {
        binding.setProgressVisibility(View.GONE);
    }

    @Override
    public void onError() {
        binding.setProgressTxt(getString(R.string.text_error));
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
