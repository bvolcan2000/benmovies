package com.movies.benmovies.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import com.movies.benmovies.R
import com.movies.benmovies.databinding.ItemTrendingPosterBinding
import com.movies.benmovies.models.entity.TrendingMovie
import com.movies.benmovies.view.ui.details.movie.TrendingMovieDetailActivity

class TrendingMovieListAdapter : RecyclerView.Adapter<TrendingMovieListAdapter.TrendingMovieListViewHolder>() {

  private val items: MutableList<TrendingMovie> = arrayListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMovieListViewHolder {
    val binding = parent.binding<ItemTrendingPosterBinding>(R.layout.item_trending_poster)
    return TrendingMovieListViewHolder(binding).apply {
      binding.root.setOnClickListener {
        val movie = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
          ?: return@setOnClickListener
        TrendingMovieDetailActivity.startActivityModel(it.context, items[movie])
        R.layout.item_poster
      }
    }
  }

  override fun onBindViewHolder(holder: TrendingMovieListViewHolder, position: Int) {
    with(holder.binding) {
      movie = items[position]
      executePendingBindings()
    }
  }

  fun addMovieList(movies: List<TrendingMovie>) {
    val previousItemSize = items.size
    items.addAll(movies)
    notifyItemRangeInserted(previousItemSize, movies.size)
  }

  override fun getItemCount(): Int = items.size

  class TrendingMovieListViewHolder(val binding: ItemTrendingPosterBinding) : RecyclerView
  .ViewHolder(binding.root)
}
