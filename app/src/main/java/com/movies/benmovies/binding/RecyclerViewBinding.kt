package com.movies.benmovies.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.movies.benmovies.R
import com.movies.benmovies.extension.setQuery
import com.movies.benmovies.extension.visible
import com.movies.benmovies.models.Keyword
import com.movies.benmovies.models.Review
import com.movies.benmovies.models.Video
import com.movies.benmovies.models.entity.Movie
import com.movies.benmovies.models.entity.TrendingMovie
import com.movies.benmovies.models.entity.TrendingTv
import com.movies.benmovies.models.entity.Tv
import com.movies.benmovies.view.adapter.*
import com.movies.benmovies.view.ui.main.MainActivityViewModel
import com.movies.benmovies.view.ui.main.SearchActivityViewModel
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty

object RecyclerViewBinding {

  @JvmStatic
  @BindingAdapter("adapter")
  fun bindAdapter(view: RecyclerView, baseAdapter: BaseAdapter) {
    view.adapter = baseAdapter
  }

  @JvmStatic
  @BindingAdapter("adapterMovieList")
  fun bindAdapterMovieList(view: RecyclerView, movies: List<Movie>?) {
    movies.whatIfNotNull {
      (view.adapter as? MovieListAdapter)?.addMovieList(it)
    }
  }

  @JvmStatic
  @BindingAdapter("adapterTrendingMovieList")
  fun bindAdapterTrendingMovieList(view: RecyclerView, movies: List<TrendingMovie>?) {
    movies.whatIfNotNull {
      (view.adapter as? TrendingMovieListAdapter)?.addMovieList(it)
    }
  }

  @JvmStatic
  @BindingAdapter("paginationMovieList")
  fun paginationMovieList(view: RecyclerView, viewModel: MainActivityViewModel) {
    RecyclerViewPaginator(
      recyclerView = view,
      isLoading = { viewModel.isMovieListLoading.get() },
      loadMore = { viewModel.postMoviePage(it) },
      onLast = { false }
    ).run {
      threshold = 4
      currentPage = 1
    }
  }

  @JvmStatic
  @BindingAdapter("paginationSearchMovieList")
  fun paginationSearchMovieList(view: RecyclerView, viewModel: SearchActivityViewModel) {
    RecyclerViewPaginator(
      recyclerView = view,
      isLoading = { viewModel.isMovieListLoading.get() },
      loadMore = { viewModel.postSearchMoviePage(it) },
      onLast = { false }
    ).run {
      threshold = 4
      currentPage = 1
    }
  }

  @JvmStatic
  @BindingAdapter("paginationTrendingMovieList")
  fun paginationTrendingMovieList(view: RecyclerView, viewModel: MainActivityViewModel) {
    RecyclerViewPaginator(
      recyclerView = view,
      isLoading = { viewModel.isTrendingMovieListLoading.get() },
      loadMore = { viewModel.postTrendingMoviePage(it) },
      onLast = { false }
    ).run {
      threshold = 4
      currentPage = 1
    }
  }


  @JvmStatic
  @BindingAdapter("adapterTvList")
  fun bindAdapterTvList(view: RecyclerView, tvs: List<Tv>?) {
    tvs.whatIfNotNull { items ->
      view.adapter.whatIfNotNullAs<TvListAdapter> {
        it.addTvList(items)
      }
    }
  }

  @JvmStatic
  @BindingAdapter("adapterTrendingTvList")
  fun bindAdapterTrendingTvList(view: RecyclerView, tvs: List<TrendingTv>?) {
    tvs.whatIfNotNull { items ->
      view.adapter.whatIfNotNullAs<TrendingTvListAdapter> {
        it.addTvList(items)
      }
    }
  }

  @JvmStatic
  @BindingAdapter("paginationTvList")
  fun paginationTvList(view: RecyclerView, viewModel: MainActivityViewModel) {
    RecyclerViewPaginator(
      recyclerView = view,
      isLoading = { viewModel.isTvListLoading.get() },
      loadMore = { viewModel.postTvPage(it) },
      onLast = { false }
    ).run {
      threshold = 4
      currentPage = 1
    }
  }

  @JvmStatic
  @BindingAdapter("paginationSearchTvList")
  fun paginationSearchTvList(view: RecyclerView, viewModel: SearchActivityViewModel) {
    RecyclerViewPaginator(
      recyclerView = view,
      isLoading = { viewModel.isTvListLoading.get() },
      loadMore = { viewModel.postSearchTvPage(it) },
      onLast = { false }
    ).run {
      threshold = 4
      currentPage = 1
    }
  }

  @JvmStatic
  @BindingAdapter("paginationTrendingTvList")
  fun paginationTrendingTvList(view: RecyclerView, viewModel: MainActivityViewModel) {
    RecyclerViewPaginator(
      recyclerView = view,
      isLoading = { viewModel.isTrendingTvListLoading.get() },
      loadMore = { viewModel.postTvPage(it) },
      onLast = { false }
    ).run {
      threshold = 4
      currentPage = 1
    }
  }

  @JvmStatic
  @BindingAdapter("adapterVideoList")
  fun bindAdapterVideoList(view: RecyclerView, videos: List<Video>?) {
    videos.whatIfNotNullOrEmpty { items ->
      view.adapter.whatIfNotNullAs<VideoListAdapter> {
        it.addVideoList(items)
        view.visible()
      }
    }
  }

  @JvmStatic
  @BindingAdapter("adapterReviewList")
  fun bindAdapterReviewList(view: RecyclerView, reviews: List<Review>?) {
    view.setHasFixedSize(true)
    reviews.whatIfNotNullOrEmpty { items ->
      view.adapter.whatIfNotNullAs<ReviewListAdapter> {
        it.addReviewList(items)
        view.visible()
      }
    }
  }

  @JvmStatic
  @BindingAdapter("mapKeywordList")
  fun bindMapKeywordList(chipGroup: ChipGroup, keywords: List<Keyword>?) {
    keywords.whatIfNotNullOrEmpty {
      chipGroup.visible()
      for (keyword in it) {
        chipGroup.addView(
          Chip(chipGroup.context).apply {
            text = keyword.name
            isCheckable = false
            setTextAppearanceResource(R.style.ChipTextStyle)
            setChipBackgroundColorResource(R.color.colorPrimary)
          }
        )
      }
    }
  }

  @JvmStatic
  @BindingAdapter("queryTv")
  fun queryTv(view: RecyclerView, text: LiveData<String>) {
    text.value.whatIfNotNull {
      text.value?.let { it1 -> view.setQuery(it1) }
    }
  }

  @JvmStatic
  @BindingAdapter("queryMovie")
  fun queryMovie(view: RecyclerView, text: LiveData<String>) {
    text.value.whatIfNotNull {
      text.value?.let { it1 -> view.setQuery(it1) }
    }
  }

}
