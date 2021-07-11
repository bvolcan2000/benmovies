package com.movies.benmovies.di

import com.movies.benmovies.view.ui.details.movie.MovieDetailViewModel
import com.movies.benmovies.view.ui.details.movie.SearchMovieDetailViewModel
import com.movies.benmovies.view.ui.details.movie.TrendingMovieDetailViewModel
import com.movies.benmovies.view.ui.details.tv.SearchTvDetailViewModel
import com.movies.benmovies.view.ui.details.tv.TrendingTvDetailViewModel
import com.movies.benmovies.view.ui.details.tv.TvDetailViewModel
import com.movies.benmovies.view.ui.main.MainActivityViewModel
import com.movies.benmovies.view.ui.main.SearchActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { MainActivityViewModel(get(), get()) }
  viewModel { SearchActivityViewModel(get()) }
  viewModel { MovieDetailViewModel(get()) }
  viewModel { TrendingMovieDetailViewModel(get()) }
  viewModel { SearchMovieDetailViewModel(get()) }
  viewModel { TvDetailViewModel(get()) }
  viewModel { TrendingTvDetailViewModel(get()) }
  viewModel { SearchTvDetailViewModel(get()) }
}
