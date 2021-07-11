package com.movies.benmovies.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import com.movies.benmovies.R
import com.movies.benmovies.databinding.ItemPosterBinding
import com.movies.benmovies.models.entity.Movie
import com.movies.benmovies.view.ui.details.movie.MovieDetailActivity

class SearchMovieListAdapter : RecyclerView.Adapter<SearchMovieListAdapter.SearchMovieListViewHolder>() {

  private val items: MutableList<Movie> = arrayListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieListViewHolder {
    val binding = parent.binding<ItemPosterBinding>(R.layout.item_poster)
    return SearchMovieListViewHolder(binding).apply {
      binding.root.setOnClickListener {
        val movie = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
          ?: return@setOnClickListener
        MovieDetailActivity.startActivityModel(it.context, items[movie])
        R.layout.item_poster
      }
    }
  }

  override fun onBindViewHolder(holder: SearchMovieListViewHolder, position: Int) {
    with(holder.binding) {
      movie = items[position]
      executePendingBindings()
    }
  }

  fun addMovieList(movies: List<Movie>) {
    val previousItemSize = items.size
    items.addAll(movies)
    notifyItemRangeInserted(previousItemSize, movies.size)
  }

  override fun getItemCount(): Int = items.size

  class SearchMovieListViewHolder(val binding: ItemPosterBinding) : RecyclerView.ViewHolder(binding
    .root)
}
