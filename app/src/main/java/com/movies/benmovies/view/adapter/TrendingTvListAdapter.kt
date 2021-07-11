
package com.movies.benmovies.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import com.movies.benmovies.R
import com.movies.benmovies.databinding.ItemTrendingTvBinding
import com.movies.benmovies.models.entity.TrendingTv
import com.movies.benmovies.view.ui.details.tv.TrendingTvDetailActivity

class TrendingTvListAdapter : RecyclerView.Adapter<TrendingTvListAdapter.TrendingTvListViewHolder>() {

  private val items: MutableList<TrendingTv> = arrayListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingTvListViewHolder {
    val binding = parent.binding<ItemTrendingTvBinding>(R.layout.item_trending_tv)
    return TrendingTvListViewHolder(binding).apply {
      binding.root.setOnClickListener {
        val trendingTv = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
          ?: return@setOnClickListener

        TrendingTvDetailActivity.startActivityModel(it.context, items[trendingTv])
      }
    }
  }

  override fun onBindViewHolder(holder: TrendingTvListViewHolder, position: Int) {
    with(holder.binding) {
      tv = items[position]
      executePendingBindings()
    }
  }

  override fun getItemCount(): Int = items.size

  fun addTvList(tvs: List<TrendingTv>) {
    val previousItemSize = items.size
    items.addAll(tvs)
    notifyItemRangeInserted(previousItemSize, tvs.size)
  }

  class TrendingTvListViewHolder(val binding: ItemTrendingTvBinding) :
    RecyclerView.ViewHolder(binding.root)
}
