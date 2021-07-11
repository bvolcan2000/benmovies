package com.movies.benmovies.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydoves.bindables.BindingFragment
import com.movies.benmovies.R
import com.movies.benmovies.databinding.SearchFragmentTvBinding
import com.movies.benmovies.view.adapter.SearchTvListAdapter
import com.movies.benmovies.view.adapter.TvListAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class SearchTvListFragment :
  BindingFragment<SearchFragmentTvBinding>(R.layout.search_fragment_tv) {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding {
      viewModel = getViewModel<SearchActivityViewModel>().apply { postSearchTvPage(1) }
      lifecycleOwner = this@SearchTvListFragment
      adapter = TvListAdapter()
    }.root
  }
}
