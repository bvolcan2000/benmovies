package com.movies.benmovies.view.ui.main

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.movies.benmovies.base.DispatchViewModel
import com.movies.benmovies.models.entity.Movie
import com.movies.benmovies.models.entity.Tv
import com.movies.benmovies.repository.SearchRepository
import com.movies.benmovies.utils.QuerySingleton


import timber.log.Timber

class SearchActivityViewModel constructor(
  private val searchRepository: SearchRepository) : DispatchViewModel() {

  private var moviePageLiveData: MutableLiveData<Int> = MutableLiveData()
  var movieListLiveData: LiveData<List<Movie>>

  private var tvPageLiveData: MutableLiveData<Int> = MutableLiveData()
  var tvListLiveData: LiveData<List<Tv>>

  val isMovieListLoading: ObservableBoolean = ObservableBoolean(false)
  val isTvListLoading: ObservableBoolean = ObservableBoolean(false)

  var visibilityMovieSearch = MutableLiveData<Int>()
  var visibilityTvSearch = MutableLiveData<Int>()
  var query: String = ""

  init {
    val querySingleton : QuerySingleton = QuerySingleton.instance
    query = querySingleton.query

    Timber.d("injection SearchActivityViewModel")
    this.movieListLiveData = moviePageLiveData.switchMap { page ->
      isMovieListLoading.set(true)
      visibilityMovieSearch.value = View.VISIBLE
      launchOnViewModelScope {
        searchRepository.loadMovies(page, query) {
          isMovieListLoading.set(false)
          visibilityMovieSearch.postValue(View.INVISIBLE)
        }.asLiveData()
      }
    }

    this.tvListLiveData = tvPageLiveData.switchMap { page ->
      isTvListLoading.set(true)
      visibilityMovieSearch.value = View.VISIBLE
      launchOnViewModelScope {
        searchRepository.loadTvs(page, query) {
          isTvListLoading.set(false)
          visibilityTvSearch.postValue(View.INVISIBLE)
        }.asLiveData()
      }
    }
  }

  fun postSearchMoviePage(id: Int) = moviePageLiveData.postValue(id)
  fun postSearchTvPage(id: Int) = tvPageLiveData.postValue(id)
}
