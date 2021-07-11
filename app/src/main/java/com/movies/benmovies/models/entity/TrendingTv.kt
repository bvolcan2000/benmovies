package com.movies.benmovies.models.entity

import android.os.Parcelable
import androidx.room.Entity
import com.movies.benmovies.models.Keyword
import com.movies.benmovies.models.Review
import com.movies.benmovies.models.Video
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = [("id")])
data class TrendingTv(
  var page: Int,
  var keywords: List<Keyword>? = ArrayList(),
  var videos: List<Video>? = ArrayList(),
  var reviews: List<Review>? = ArrayList(),
  val poster_path: String?,
  val popularity: Float,
  val id: Int,
  val backdrop_path: String?,
  val vote_average: Float,
  val overview: String,
  val first_air_date: String?,
  val origin_country: List<String>,
  val genre_ids: List<Int>,
  val original_language: String,
  val vote_count: Int,
  val name: String,
  val original_name: String
) : Parcelable
