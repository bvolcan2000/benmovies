package com.movies.benmovies.api.service

import com.movies.benmovies.models.network.SearchMovieResponse
import com.movies.benmovies.models.network.SearchTvResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/3/search/movie?language=es-Mx&sort_by=popularity.desc")
    suspend fun fetchSearchMovie(@Query("query") query: String,@Query("page") page: Int):
            ApiResponse<SearchMovieResponse>
    @GET("/3/search/tv?language=es-Mx&sort_by=popularity.desc")
    suspend fun fetchSearchTv(@Query("query") query: String, @Query("page") page: Int): ApiResponse<SearchTvResponse>
}