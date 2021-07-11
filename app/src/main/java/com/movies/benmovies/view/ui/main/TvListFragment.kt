package com.movies.benmovies.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydoves.bindables.BindingFragment
import com.movies.benmovies.R
import com.movies.benmovies.databinding.MainFragmentTvBinding
import com.movies.benmovies.view.adapter.TvListAdapter
import org.koin.android.viewmodel.ext.android.getViewModel
import com.movies.benmovies.view.ui.main.MainActivityViewModel

class TvListFragment :
  BindingFragment<MainFragmentTvBinding>(R.layout.main_fragment_tv) {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding {
      viewModel = getViewModel<MainActivityViewModel>().apply { postTvPage(1) }
      lifecycleOwner = this@TvListFragment
      adapter = TvListAdapter()
    }.root
  }
}
