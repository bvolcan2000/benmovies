package com.movies.benmovies.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.movies.benmovies.models.entity.Movie
import com.movies.benmovies.models.entity.TrendingMovie
import com.movies.benmovies.models.entity.TrendingTv
import com.movies.benmovies.models.entity.Tv
import com.movies.benmovies.room.converters.IntegerListConverter
import com.movies.benmovies.room.converters.KeywordListConverter
import com.movies.benmovies.room.converters.ReviewListConverter
import com.movies.benmovies.room.converters.StringListConverter
import com.movies.benmovies.room.converters.VideoListConverter

@Database(
  entities = [(Movie::class), (Tv::class), (TrendingMovie::class), (TrendingTv::class)],
  version = 1, exportSchema = false
)
@TypeConverters(
  value = [
    (StringListConverter::class), (IntegerListConverter::class),
    (KeywordListConverter::class), (VideoListConverter::class), (ReviewListConverter::class)
  ]
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
  abstract fun tvDao(): TvDao
  abstract fun trendingMovieDao(): TrendingMovieDao
  abstract fun trendingTvDao(): TrendingTvDao
}
