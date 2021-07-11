package com.movies.benmovies.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.movies.benmovies.api.RequestInterceptor
import com.movies.benmovies.api.service.*
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
  single {
    OkHttpClient.Builder()
      .addInterceptor(RequestInterceptor())
      .addNetworkInterceptor(StethoInterceptor())
      .build()
  }

  single {
    Retrofit.Builder()
      .client(get<OkHttpClient>())
      .baseUrl("https://api.themoviedb.org/")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
      .build()
  }

  single {
    get<Retrofit>().create(TheDiscoverService::class.java)
  }

  single {
    get<Retrofit>().create(MovieService::class.java)
  }

  single {
    get<Retrofit>().create(TvService::class.java)
  }

  single {
    get<Retrofit>().create(SearchService::class.java)
  }

  single {
    get<Retrofit>().create(TrendingService::class.java)
  }

}
