package com.movies.benmovies.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.movies.benmovies.models.entity.TrendingMovie

@Dao
interface TrendingMovieDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovieList(movie: List<TrendingMovie>)

  @Update
  fun updateMovie(movie: TrendingMovie)



  @Query("SELECT * FROM TrendingMovie WHERE id = :id_")
  fun getMovie(id_: Int): TrendingMovie

  @Query("SELECT * FROM TrendingMovie WHERE page = :page_")
  fun getMovieList(page_: Int): List<TrendingMovie>
}
