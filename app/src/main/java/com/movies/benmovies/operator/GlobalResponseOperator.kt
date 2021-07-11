package com.movies.benmovies.operator

import android.app.Application
import android.widget.Toast
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.map
import com.skydoves.sandwich.message
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import com.movies.benmovies.mapper.ErrorResponseMapper
import com.movies.benmovies.models.network.TheMovieErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GlobalResponseOperator<T> constructor(
  private val application: Application
) : ApiResponseSuspendOperator<T>() {

  override suspend fun onSuccess(apiResponse: ApiResponse.Success<T>) = Unit

  override suspend fun onError(apiResponse: ApiResponse.Failure.Error<T>) =
    withContext(Dispatchers.IO) {
      apiResponse.run {
        Timber.d(message())

        when (statusCode) {
          StatusCode.InternalServerError -> toast("InternalServerError")
          StatusCode.BadGateway -> toast("BadGateway")
          else -> toast("$statusCode(${statusCode.code}): ${message()}")
        }

       map(ErrorResponseMapper) {
          Timber.d(message())
        }
      }
    }

   override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<T>) =
    withContext(Dispatchers.Main) {
      apiResponse.run {
        Timber.d(message())
        toast(message())
      }
    }

  private fun toast(message: String) {
  }
}
