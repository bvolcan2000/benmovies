package com.movies.benmovies.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydoves.bindables.BindingFragment
import com.movies.benmovies.R
import com.movies.benmovies.databinding.MainFragmentMovieBinding
import com.movies.benmovies.view.adapter.MovieListAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class MovieListFragment :
  BindingFragment<MainFragmentMovieBinding>(R.layout.main_fragment_movie) {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding {
      viewModel = getViewModel<MainActivityViewModel>().apply { postMoviePage(1) }
      lifecycleOwner = this@MovieListFragment
      adapter = MovieListAdapter()
    }.root
  }
}
