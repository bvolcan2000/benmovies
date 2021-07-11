
package com.movies.benmovies.view.ui.details.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.movies.benmovies.models.Keyword
import com.movies.benmovies.models.Review
import com.movies.benmovies.repository.TvRepository
import com.movies.benmovies.models.Video
import com.movies.benmovies.base.DispatchViewModel
import timber.log.Timber

class TvDetailViewModel constructor(
  private val tvRepository: TvRepository
) : DispatchViewModel() {

  private val tvIdLiveData: MutableLiveData<Int> = MutableLiveData()
  val keywordListLiveData: LiveData<List<Keyword>?>
  val videoListLiveData: LiveData<List<Video>?>
  val reviewListLiveData: LiveData<List<Review>?>

  init {
    Timber.d("Injection TvDetailViewModel")

    this.keywordListLiveData = tvIdLiveData.switchMap { id ->
      launchOnViewModelScope {
        tvRepository.loadKeywordList(id).asLiveData()
      }
    }

    this.videoListLiveData = tvIdLiveData.switchMap { id ->
      launchOnViewModelScope {
        tvRepository.loadVideoList(id).asLiveData()
      }
    }

    this.reviewListLiveData = tvIdLiveData.switchMap { id ->
      launchOnViewModelScope {
        tvRepository.loadReviewsList(id).asLiveData()
      }
    }
  }

  fun postTvId(id: Int) = tvIdLiveData.postValue(id)
}
