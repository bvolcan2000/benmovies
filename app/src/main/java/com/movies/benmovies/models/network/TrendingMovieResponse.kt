
package com.movies.benmovies.models.network

import com.movies.benmovies.models.NetworkResponseModel
import com.movies.benmovies.models.entity.TrendingMovie

data class TrendingMovieResponse(
  val page: Int,
  val results: List<TrendingMovie>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
