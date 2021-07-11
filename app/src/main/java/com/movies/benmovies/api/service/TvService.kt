package com.movies.benmovies.api.service

import com.skydoves.sandwich.ApiResponse
import com.movies.benmovies.models.network.KeywordListResponse
import com.movies.benmovies.models.network.ReviewListResponse
import com.movies.benmovies.models.network.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TvService {

  @GET("/3/tv/{tv_id}/keywords?language=es-Mx")
  suspend fun fetchKeywords(@Path("tv_id") id: Int): ApiResponse<KeywordListResponse>
  @GET("/3/tv/{tv_id}/videos?language=es-Mx")
  suspend fun fetchVideos(@Path("tv_id") id: Int): ApiResponse<VideoListResponse>
  @GET("/3/tv/{tv_id}/reviews?language=es-Mx")
  suspend fun fetchReviews(@Path("tv_id") id: Int): ApiResponse<ReviewListResponse>
}
