package com.movies.benmovies.models.network

import com.movies.benmovies.models.Keyword
import com.movies.benmovies.models.NetworkResponseModel

data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel
