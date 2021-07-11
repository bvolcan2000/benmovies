package com.movies.benmovies.models.network

import com.movies.benmovies.models.NetworkResponseModel
import com.movies.benmovies.models.Review

class ReviewListResponse(
  val id: Int,
  val page: Int,
  val results: List<Review>,
  val total_pages: Int,
  val total_results: Int
) : NetworkResponseModel
