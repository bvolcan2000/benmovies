package com.movies.benmovies.view.ui.main

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.movies.benmovies.base.DispatchViewModel
import com.movies.benmovies.models.entity.Movie
import com.movies.benmovies.models.entity.TrendingMovie
import com.movies.benmovies.models.entity.TrendingTv
import com.movies.benmovies.models.entity.Tv
import com.movies.benmovies.repository.DiscoverRepository
import com.movies.benmovies.repository.TrendingRepository
import timber.log.Timber

class MainActivityViewModel constructor(
  private val discoverRepository: DiscoverRepository,
  private val trendingRepository: TrendingRepository
) : DispatchViewModel() {

  private var moviePageLiveData: MutableLiveData<Int> = MutableLiveData()
  val movieListLiveData: LiveData<List<Movie>>

  private var tvPageLiveData: MutableLiveData<Int> = MutableLiveData()
  val tvListLiveData: LiveData<List<Tv>>

  private var trendingMoviePageLiveData: MutableLiveData<Int> = MutableLiveData()
  val trendingMovieListLiveData: LiveData<List<TrendingMovie>>

  private var trendingTvPageLiveData: MutableLiveData<Int> = MutableLiveData()
  val trendingTvListLiveData: LiveData<List<TrendingTv>>

  val isMovieListLoading: ObservableBoolean = ObservableBoolean(false)
  val isTvListLoading: ObservableBoolean = ObservableBoolean(false)
  val isTrendingMovieListLoading: ObservableBoolean = ObservableBoolean(false)
  val isTrendingTvListLoading: ObservableBoolean = ObservableBoolean(false)

  var visibilityMovie = MutableLiveData<Int>()
  var visibilityTv = MutableLiveData<Int>()
  var visibilityTrendingMovie = MutableLiveData<Int>()
  var visibilityTrendingTv = MutableLiveData<Int>()
  init {

    Timber.d("injection MainActivityViewModel")

    this.movieListLiveData = moviePageLiveData.switchMap { page ->
      isMovieListLoading.set(true)
      visibilityMovie.value = View.VISIBLE
      launchOnViewModelScope {
        discoverRepository.loadMovies(page) {
          isMovieListLoading.set(false)
          visibilityMovie.postValue(View.INVISIBLE)
        }.asLiveData()
      }
    }

    this.tvListLiveData = tvPageLiveData.switchMap { page ->
      isTvListLoading.set(true)
      visibilityTv.value = View.VISIBLE
      launchOnViewModelScope {
        discoverRepository.loadTvs(page) {
          isTvListLoading.set(false)
          visibilityTv.postValue(View.INVISIBLE)
        }.asLiveData()
      }
    }

    this.trendingMovieListLiveData = trendingMoviePageLiveData.switchMap { page ->
      isTrendingMovieListLoading.set(true)
      visibilityTrendingMovie.value = View.VISIBLE
      launchOnViewModelScope {
        trendingRepository.loadMovies(page) {
          isTrendingMovieListLoading.set(false)
          visibilityTrendingMovie.postValue(View.INVISIBLE)
        }.asLiveData()
      }
    }

    this.trendingTvListLiveData = trendingTvPageLiveData.switchMap { page ->
      isTrendingTvListLoading.set(true)
      visibilityTrendingTv.value = View.VISIBLE
      launchOnViewModelScope {
        trendingRepository.loadTvs(page) {
          isTrendingTvListLoading.set(false)
          visibilityTrendingTv.postValue(View.INVISIBLE)
        }.asLiveData()
      }
    }
  }


  fun postMoviePage(page: Int) = moviePageLiveData.postValue(page)

  fun postTvPage(page: Int) = tvPageLiveData.postValue(page)

  fun postTrendingMoviePage(page: Int) = trendingMoviePageLiveData.postValue(page)

  fun postTrendingTvPage(page: Int) = trendingTvPageLiveData.postValue(page)
}
