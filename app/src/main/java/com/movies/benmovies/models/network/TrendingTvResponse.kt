package com.movies.benmovies.models.network

import com.movies.benmovies.models.NetworkResponseModel
import com.movies.benmovies.models.entity.TrendingTv

data class TrendingTvResponse(
  val page: Int,
  val results: List<TrendingTv>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel
