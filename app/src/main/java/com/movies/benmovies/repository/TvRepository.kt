package com.movies.benmovies.repository

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.suspendOnSuccess
import com.movies.benmovies.api.service.TvService
import com.movies.benmovies.models.Keyword
import com.movies.benmovies.models.Review
import com.movies.benmovies.models.Video
import com.movies.benmovies.room.TvDao
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class TvRepository constructor(
  private val tvService: TvService,
  private val tvDao: TvDao
) : Repository {

  init {
    Timber.d("Injection TvRepository")
  }

  @WorkerThread
  fun loadKeywordList(id: Int) = flow {
    val tv = tvDao.getTv(id)

    var keywords : List<Keyword>? = null
    if (tv.keywords !=  null)
      keywords = tv.keywords

    if (keywords.isNullOrEmpty()) {
      val response = tvService.fetchKeywords(id)
      response.suspendOnSuccess {
        data.whatIfNotNull { data ->
          keywords = data.keywords
          tv.keywords = keywords
          tvDao.updateTv(tv)
          emit(keywords)
        }
      }
    } else {
      emit(keywords)
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadVideoList(id: Int) = flow {
    val tv = tvDao.getTv(id)
    var videos : List<Video>? = null
    if (tv.videos !=  null)
        videos = tv.videos

    if (videos.isNullOrEmpty()) {
      val response = tvService.fetchVideos(id)
      response.suspendOnSuccess {
        data.whatIfNotNull { data ->
          videos = data.results
          tv.videos = videos
          tvDao.updateTv(tv)
          emit(videos)
        }
      }
    } else {
      emit(videos)
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadReviewsList(id: Int) = flow {
    val tv = tvDao.getTv(id)
    var reviews : List<Review>? = null
    if (tv.reviews !=  null)
      reviews = tv.reviews

    if (reviews.isNullOrEmpty()) {
      val response = tvService.fetchReviews(id)
      response.suspendOnSuccess {
        data.whatIfNotNull { data ->
          reviews = data.results
          tv.reviews = reviews
          tvDao.updateTv(tv)
          emit(reviews)
        }
      }
    } else {
      emit(reviews)
    }
  }.flowOn(Dispatchers.IO)
}
