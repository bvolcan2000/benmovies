package com.movies.benmovies.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydoves.bindables.BindingFragment
import com.movies.benmovies.R
import com.movies.benmovies.databinding.SearchFragmentMovieBinding
import com.movies.benmovies.view.adapter.MovieListAdapter
import com.movies.benmovies.view.adapter.SearchMovieListAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class SearchMovieListFragment :
  BindingFragment<SearchFragmentMovieBinding>(R.layout.search_fragment_movie) {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding {
      viewModel = getViewModel<SearchActivityViewModel>().apply { postSearchMoviePage(1) }
      lifecycleOwner = this@SearchMovieListFragment
      adapter = MovieListAdapter()
    }.root
  }
}
