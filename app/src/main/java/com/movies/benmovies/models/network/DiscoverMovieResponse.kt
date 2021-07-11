
package com.movies.benmovies.models.network

import com.movies.benmovies.models.NetworkResponseModel
import com.movies.benmovies.models.entity.Movie

data class DiscoverMovieResponse(
  val page: Int,
  val results: List<Movie>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
