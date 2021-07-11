package com.movies.benmovies.api
import okhttp3.Interceptor
import okhttp3.Response


internal class RequestInterceptor : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalUrl = originalRequest.url
    val url = originalUrl.newBuilder()
      .addQueryParameter("api_key", "f407aa948d24df3ceaf3fb5534415162")
      .build()

    val requestBuilder = originalRequest.newBuilder().url(url)
    val request = requestBuilder.build()

    return chain.proceed(request)
  }
}
