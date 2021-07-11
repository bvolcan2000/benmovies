
package com.movies.benmovies.repository

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.suspendOnSuccess
import com.movies.benmovies.api.service.TrendingService
import com.movies.benmovies.room.TrendingMovieDao
import com.movies.benmovies.room.TrendingTvDao
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class TrendingRepository constructor(
  private val trendingService: TrendingService,
  private val trendingMovieDao: TrendingMovieDao,
  private val trendingTvDao: TrendingTvDao
) : Repository {

  init {
    Timber.d("Injection TrendingRepository")
  }

  @WorkerThread
  fun loadMovies(page: Int, success: () -> Unit) = flow {
    var movies = trendingMovieDao.getMovieList(page)
    if (movies.isEmpty()) {
      val response = trendingService.fetchTrendingMovie(page)
      response.suspendOnSuccess {
        data.whatIfNotNull { data ->
          movies = data.results
          movies.forEach { it.page = page }
          trendingMovieDao.insertMovieList(movies)
          emit(movies)
          success()
        }
      }
    } else {
      emit(movies)
      success()
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadTvs(page: Int, success: () -> Unit) = flow {
    var tvs = trendingTvDao.getTvList(page)
    if (tvs.isEmpty()) {
      val response = trendingService.fetchTrendingTv(page)
      response.suspendOnSuccess {
        data.whatIfNotNull { data ->
          tvs = data.results
          tvs.forEach { it.page = page }
          trendingTvDao.insertTv(tvs)
          emit(tvs)
          success()
        }
      }
    } else {
      emit(tvs)
      success()
    }
  }.flowOn(Dispatchers.IO)
}
