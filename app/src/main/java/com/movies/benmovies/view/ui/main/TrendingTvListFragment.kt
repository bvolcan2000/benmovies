package com.movies.benmovies.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydoves.bindables.BindingFragment
import com.movies.benmovies.R
import com.movies.benmovies.databinding.TrendingFragmentTvBinding
import com.movies.benmovies.view.adapter.TrendingTvListAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class TrendingTvListFragment :
  BindingFragment<TrendingFragmentTvBinding>(R.layout.trending_fragment_tv) {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding {
      viewModel = getViewModel<MainActivityViewModel>().apply { postTrendingTvPage(1) }
      lifecycleOwner = this@TrendingTvListFragment
      adapter = TrendingTvListAdapter()
    }.root
  }
}
