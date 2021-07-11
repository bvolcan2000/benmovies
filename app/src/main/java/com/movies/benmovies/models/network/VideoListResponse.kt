package com.movies.benmovies.models.network

import com.movies.benmovies.models.NetworkResponseModel
import com.movies.benmovies.models.Video

data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel
