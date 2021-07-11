package com.movies.benmovies.di

import com.movies.benmovies.repository.*
import org.koin.dsl.module

val repositoryModule = module {
  single { DiscoverRepository(get(), get(), get()) }
  single { TrendingRepository(get(), get(), get()) }
  single { SearchRepository(get(), get(), get()) }
  single { MovieRepository(get(), get()) }
  single { TvRepository(get(), get()) }
}
