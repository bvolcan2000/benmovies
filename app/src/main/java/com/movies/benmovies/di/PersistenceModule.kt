package com.movies.benmovies.di

import androidx.room.Room
import com.movies.benmovies.room.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {
  single {
    Room
      .databaseBuilder(androidApplication(), AppDatabase::class.java, "TheMovies.db")
      .allowMainThreadQueries()
      .build()
  }

  single { get<AppDatabase>().movieDao() }
  single { get<AppDatabase>().tvDao() }
  single { get<AppDatabase>().trendingMovieDao() }
  single { get<AppDatabase>().trendingTvDao() }
}
