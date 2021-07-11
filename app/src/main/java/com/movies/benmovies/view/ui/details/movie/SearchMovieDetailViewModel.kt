package com.movies.benmovies.view.ui.details.movie

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.movies.benmovies.repository.MovieRepository
import com.movies.benmovies.models.Keyword
import com.movies.benmovies.models.Review
import com.movies.benmovies.base.DispatchViewModel
import com.movies.benmovies.models.Video
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber

class SearchMovieDetailViewModel constructor(
  private val movieRepository: MovieRepository
) : DispatchViewModel() {

  private val movieIdStateFlow: MutableStateFlow<Int> = MutableStateFlow(0)

  val keywordListLiveData: LiveData<List<Keyword>?>
  val videoListLiveData: LiveData<List<Video>?>
  val reviewListLiveData: LiveData<List<Review>?>

  init {
    Timber.d("Injection SearchMovieDetailViewModel")

    this.keywordListLiveData = movieIdStateFlow.asLiveData().switchMap { id ->
      launchOnViewModelScope {
        movieRepository.loadKeywordList(id).asLiveData()
      }
    }

    this.videoListLiveData = movieIdStateFlow.asLiveData().switchMap { id ->
      launchOnViewModelScope {
        movieRepository.loadVideoList(id).asLiveData()
      }
    }

    this.reviewListLiveData = movieIdStateFlow.asLiveData().switchMap { id ->
      launchOnViewModelScope {
        movieRepository.loadReviewsList(id).asLiveData()
      }
    }
  }

  @MainThread
  fun getMovieListFromId(id: Int) {
    movieIdStateFlow.value = id
  }
}
