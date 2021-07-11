package com.movies.benmovies.repository

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.suspendOnSuccess
import com.movies.benmovies.api.service.MovieService
import com.movies.benmovies.models.Keyword
import com.movies.benmovies.models.Review
import com.movies.benmovies.models.Video
import com.movies.benmovies.room.MovieDao
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class MovieRepository constructor(
  private val movieService: MovieService,
  private val movieDao: MovieDao
) : Repository {

  init {
    Timber.d("Injection MovieRepository")
  }

  @WorkerThread
  fun loadKeywordList(id: Int) = flow {
    val movie = movieDao.getMovie(id)

    var keywords : List<Keyword>? = null
    if (movie.keywords !=  null)
      keywords = movie.keywords

    if (keywords.isNullOrEmpty()) {
      val response = movieService.fetchKeywords(id)
      response.suspendOnSuccess {
        data.whatIfNotNull { data ->
          keywords = data.keywords
          movie.keywords = keywords
          emit(keywords)
          movieDao.updateMovie(movie)
        }
      }
    } else {
      emit(keywords)
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadVideoList(id: Int) = flow {
    val movie = movieDao.getMovie(id)
    var videos : List<Video>? = null
    if (movie.videos !=  null)
      videos = movie.videos
    if (videos.isNullOrEmpty()) {
      movieService.fetchVideos(id)
        .suspendOnSuccess {
          data.whatIfNotNull { data ->
            videos = data.results
            movie.videos = videos
            movieDao.updateMovie(movie)
            emit(videos)
          }
        }
    } else {
      emit(videos)
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadReviewsList(id: Int) = flow {
    val movie = movieDao.getMovie(id)
    var reviews : List<Review>? = null
    if (movie.reviews !=  null)
      reviews = movie.reviews

    if (reviews.isNullOrEmpty()) {
      movieService.fetchReviews(id)
        .suspendOnSuccess {
          data.whatIfNotNull { data ->
            reviews = data.results
            movie.reviews = reviews
            movieDao.updateMovie(movie)
            emit(reviews)
          }
        }
    } else {
      emit(reviews)
    }
  }.flowOn(Dispatchers.IO)
}
