package com.movies.benmovies.view.ui.details.movie

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.movies.benmovies.R
import com.movies.benmovies.databinding.ActivityMovieDetailBinding
import com.movies.benmovies.models.entity.Movie
import com.movies.benmovies.view.adapter.ReviewListAdapter
import com.movies.benmovies.view.adapter.VideoListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity :
  BindingActivity<ActivityMovieDetailBinding>(R.layout.activity_movie_detail) {

  private val vm: MovieDetailViewModel by viewModel()
  private val intentMovie: Movie by bundleNonNull(MOVIE_ID)

  @SuppressLint("MissingSuperCall")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding {
      activity = this@MovieDetailActivity
      lifecycleOwner = this@MovieDetailActivity
      viewModel = vm.apply { getMovieListFromId(intentMovie.id) }
      movie = intentMovie
      videoListAdapter = VideoListAdapter()
      reviewListAdapter = ReviewListAdapter()
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) onBackPressed()
    return false
  }

  companion object {
    private const val MOVIE_ID = "movie"
    fun startActivityModel(context: Context?, movie: Movie?) {
      context?.intentOf<MovieDetailActivity> {
        putExtra(MOVIE_ID to movie)
        startActivity(context)
      }
    }
  }
}
