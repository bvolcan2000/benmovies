
package com.movies.benmovies.repository

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.suspendOnSuccess
import com.movies.benmovies.api.service.SearchService
import com.movies.benmovies.models.entity.Movie
import com.movies.benmovies.models.entity.Tv
import com.movies.benmovies.room.MovieDao
import com.movies.benmovies.room.TvDao

import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class SearchRepository constructor(
  private val searchService: SearchService,
  private val movieDao: MovieDao,
  private val tvDao: TvDao
) : Repository {

  init {
    Timber.d("Injection SearchRepository")
  }

  @WorkerThread
  fun loadMovies(page: Int, query: String, success: () -> Unit) = flow {
    var movies: List<Movie> = emptyList()
    if (query.isNotEmpty()) {
      val response = searchService.fetchSearchMovie(query, page)
      response.suspendOnSuccess {
        data.whatIfNotNull { data ->
          movies = data.results
          movies.forEach { it.page = page }
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
  fun loadTvs(page: Int, query: String, success: () -> Unit) = flow {
    var tvs: List<Tv> = emptyList()
    if (query.isNotEmpty()) {
      val response = searchService.fetchSearchTv(query, page)
      response.suspendOnSuccess {
        data.whatIfNotNull { data ->
          tvs = data.results
          tvs.forEach { it.page = page }
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
