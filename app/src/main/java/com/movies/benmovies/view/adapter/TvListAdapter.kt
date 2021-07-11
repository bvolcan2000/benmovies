
package com.movies.benmovies.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.bindables.binding
import com.movies.benmovies.R
import com.movies.benmovies.databinding.ItemTvBinding
import com.movies.benmovies.models.entity.Tv
import com.movies.benmovies.view.ui.details.tv.TvDetailActivity

class TvListAdapter : RecyclerView.Adapter<TvListAdapter.TvListViewHolder>() {

  private val items: MutableList<Tv> = arrayListOf()
  private var query: String = ""
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvListViewHolder {
    val binding = parent.binding<ItemTvBinding>(R.layout.item_tv)
    return TvListViewHolder(binding).apply {
      binding.root.setOnClickListener {
        val tv = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
          ?: return@setOnClickListener
        TvDetailActivity.startActivityModel(it.context, items[tv])
      }
    }
  }

  override fun onBindViewHolder(holder: TvListViewHolder, position: Int) {
    with(holder.binding) {
      tv = items[position]
      executePendingBindings()
    }
  }

  override fun getItemCount(): Int = items.size

  fun setQuery(query:String)
  {
    this.query = query
    notifyDataSetChanged()
  }

  fun addTvList(tvs: List<Tv>) {
    val previousItemSize = items.size
    items.addAll(tvs)
    notifyItemRangeInserted(previousItemSize, tvs.size)

  }

  class TvListViewHolder(val binding: ItemTvBinding) :
    RecyclerView.ViewHolder(binding.root)
}
