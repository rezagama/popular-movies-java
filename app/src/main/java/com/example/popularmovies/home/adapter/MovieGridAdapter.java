package com.example.popularmovies.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ItemMovieGridBinding;
import com.example.popularmovies.home.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezagama on 6/30/17.
 */

public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MovieViewHolder> {
    private List<Result> results;
    private OnClickListener onClickListener;

    public MovieGridAdapter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        results = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieGridBinding view = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_movie_grid, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Result result = results.get(position);
        holder.bindData(result.posterPath);
        holder.view.getRoot().setOnClickListener(v ->
                onClickListener.onMovieGridClick(result));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieGridBinding view;

        private MovieViewHolder(ItemMovieGridBinding view) {
            super(view.getRoot());
            this.view = view;
        }

        private void bindData(String imgUrl){
            view.setImgUrl(imgUrl);
        }
    }

    public void setResults(List<Result> results) {
        if(results == null) return;
        this.results = results;
        notifyDataSetChanged();
    }

    public interface OnClickListener {
        void onMovieGridClick(Result result);
    }
}
