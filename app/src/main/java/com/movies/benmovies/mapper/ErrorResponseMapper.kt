package com.movies.benmovies.mapper

import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.movies.benmovies.models.network.TheMovieErrorResponse

object ErrorResponseMapper : ApiErrorModelMapper<TheMovieErrorResponse> {
  override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): TheMovieErrorResponse {
    return TheMovieErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
