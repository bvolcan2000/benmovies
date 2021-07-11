package com.movies.benmovies.api.service

import com.skydoves.sandwich.ApiResponse
import com.movies.benmovies.models.network.KeywordListResponse
import com.movies.benmovies.models.network.ReviewListResponse
import com.movies.benmovies.models.network.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

  @GET("/3/movie/{movie_id}/keywords?language=es-Mx")
  suspend fun fetchKeywords(@Path("movie_id") id: Int): ApiResponse<KeywordListResponse>
  @GET("/3/movie/{movie_id}/videos?language=es-Mx")
  suspend fun fetchVideos(@Path("movie_id") id: Int): ApiResponse<VideoListResponse>
  @GET("/3/movie/{movie_id}/reviews?language=es-Mx")
  suspend fun fetchReviews(@Path("movie_id") id: Int): ApiResponse<ReviewListResponse>
}
