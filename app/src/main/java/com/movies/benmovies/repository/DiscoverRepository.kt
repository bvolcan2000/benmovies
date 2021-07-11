
package com.movies.benmovies.repository

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.suspendOnSuccess
import com.movies.benmovies.api.service.TheDiscoverService
import com.movies.benmovies.room.MovieDao
import com.movies.benmovies.room.TvDao
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class DiscoverRepository constructor(
  private val discoverService: TheDiscoverService,
  private val movieDao: MovieDao,
  private val tvDao: TvDao
) : Repository {

  init {
    Timber.d("Injection DiscoverRepository")
  }

  @WorkerThread
  fun loadMovies(page: Int, success: () -> Unit) = flow {
    var movies = movieDao.getMovieList(page)
    if (movies.isEmpty()) {
      val response = discoverService.fetchDiscoverMovie(page)
      response.suspendOnSuccess {
        data.whatIfNotNull { data ->
          movies = data.results
          movies.forEach { it.page = page }
          movieDao.insertMovieList(movies)
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
    var tvs = tvDao.getTvList(page)
    if (tvs.isEmpty()) {
      val response = discoverService.fetchDiscoverTv(page)
      response.suspendOnSuccess {
        data.whatIfNotNull { data ->
          tvs = data.results
          tvs.forEach { it.page = page }
          tvDao.insertTv(tvs)
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
