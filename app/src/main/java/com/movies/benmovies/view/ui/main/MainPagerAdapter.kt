package com.movies.benmovies.view.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
  FragmentStateAdapter(fm, lifecycle) {

  override fun createFragment(position: Int): Fragment {
    return when (position) {
      0 -> MovieListFragment()
      1 -> TrendingMovieListFragment()
      2 -> TvListFragment()
      else -> TrendingTvListFragment()
    }
  }

  override fun getItemCount() = 4
}
