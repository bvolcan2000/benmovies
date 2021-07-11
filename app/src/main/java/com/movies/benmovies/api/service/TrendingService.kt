package com.movies.benmovies.api.service

import com.movies.benmovies.models.network.TrendingMovieResponse
import com.movies.benmovies.models.network.TrendingTvResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingService {

    @GET("/3/trending/movie/week?language=es-Mx&sort_by=popularity.desc")
    suspend fun fetchTrendingMovie(@Query("page") page: Int): ApiResponse<TrendingMovieResponse>
    @GET("/3/trending/tv/week?language=es-Mx&sort_by=popularity.desc")
    suspend fun fetchTrendingTv(@Query("page") page: Int): ApiResponse<TrendingTvResponse>
}