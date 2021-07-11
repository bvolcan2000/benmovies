package com.movies.benmovies.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.movies.benmovies.models.entity.TrendingTv

@Dao
interface TrendingTvDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertTv(tvs: List<TrendingTv>)

  @Update
  fun updateTv(tv: TrendingTv)

  @Query("SELECT * FROM TrendingTv WHERE id = :id_")
  fun getTv(id_: Int): TrendingTv

  @Query("SELECT * FROM TrendingTv WHERE page = :page_")
  fun getTvList(page_: Int): List<TrendingTv>
}
