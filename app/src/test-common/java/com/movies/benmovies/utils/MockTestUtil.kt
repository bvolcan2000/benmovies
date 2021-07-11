package com.movies.benmovies.utils

import com.movies.benmovies.models.Keyword
import com.movies.benmovies.models.Review
import com.movies.benmovies.models.Video
import com.movies.benmovies.models.entity.Movie
import com.movies.benmovies.models.entity.Tv

class MockTestUtil {
  companion object {
    fun mockMovie(keywords: List<Keyword> = emptyList(), videos: List<Video> = emptyList(), reviews: List<Review> = emptyList()) = Movie(1, keywords, videos, reviews, "", false, "", "", ArrayList(), 123, "", "", "", "", 0f, 0, false, 0f)
    fun mockTv(keywords: List<Keyword> = emptyList(), videos: List<Video> = emptyList(), reviews: List<Review> = emptyList()) = Tv(1, keywords, videos, reviews, "", 0f, 123, "", 0f, "", "", ArrayList(), ArrayList(), "", 1, "", "")
    fun mockMovieList(): List<Movie> {
      val movies = ArrayList<Movie>()
      movies.add(mockMovie())
      movies.add(mockMovie())
      movies.add(mockMovie())
      return movies
    }

    fun mockTvList(): List<Tv> {
      val tvs = ArrayList<Tv>()
      tvs.add(mockTv())
      tvs.add(mockTv())
      tvs.add(mockTv())
      return tvs
    }

    fun mockKeywordList(): List<Keyword> {
      val keywords = ArrayList<Keyword>()
      keywords.add(Keyword(100, "keyword0"))
      keywords.add(Keyword(101, "keyword1"))
      keywords.add(Keyword(102, "keyword2"))
      return keywords
    }

    fun mockVideoList(): List<Video> {
      val videos = ArrayList<Video>()
      videos.add(Video("123", "video0", "", "", 0, ""))
      videos.add(Video("123", "video0", "", "", 0, ""))
      return videos
    }

    fun mockReviewList(): List<Review> {
      val reviews = ArrayList<Review>()
      reviews.add(Review("123", "", "", ""))
      reviews.add(Review("123", "", "", ""))
      return reviews
    }

  }
}
