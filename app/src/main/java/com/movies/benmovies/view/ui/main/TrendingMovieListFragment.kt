package com.movies.benmovies.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydoves.bindables.BindingFragment
import com.movies.benmovies.R
import com.movies.benmovies.databinding.TrendingFragmentMovieBinding
import com.movies.benmovies.view.adapter.TrendingMovieListAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class TrendingMovieListFragment :
  BindingFragment<TrendingFragmentMovieBinding>(R.layout.trending_fragment_movie) {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding {
      viewModel = getViewModel<MainActivityViewModel>().apply { postTrendingMoviePage(1) }
      lifecycleOwner = this@TrendingMovieListFragment
      adapter = TrendingMovieListAdapter()
    }.root
  }
}
