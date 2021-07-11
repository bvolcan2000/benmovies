package com.movies.benmovies.api.service

import com.skydoves.sandwich.ApiResponse
import com.movies.benmovies.models.network.DiscoverMovieResponse
import com.movies.benmovies.models.network.DiscoverTvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheDiscoverService {

  @GET("/3/discover/movie?language=es-Mx&sort_by=popularity.desc")
  suspend fun fetchDiscoverMovie(@Query("page") page: Int): ApiResponse<DiscoverMovieResponse>
  @GET("/3/discover/tv?language=es-Mx&sort_by=popularity.desc")
  suspend fun fetchDiscoverTv(@Query("page") page: Int): ApiResponse<DiscoverTvResponse>
}
