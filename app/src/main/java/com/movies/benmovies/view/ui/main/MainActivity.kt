package com.movies.benmovies.view.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.viewpager2.widget.ViewPager2
import com.movies.benmovies.R
import com.movies.benmovies.databinding.ActivityMainBinding
import com.movies.benmovies.view.ui.details.movie.MovieDetailActivity
import com.skydoves.bindables.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
  val extraQuery = "com.movies.benmovies.QUERY"
  @SuppressLint("MissingSuperCall")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initializeUI()
  }

  private fun initializeUI() {
    with(binding.mainViewpager) {
      adapter = MainPagerAdapter(supportFragmentManager, lifecycle)
      registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) = Unit
        override fun onPageScrolled(
          position: Int,
          positionOffset: Float,
          positionOffsetPixels: Int
        ) = Unit

        override fun onPageSelected(position: Int) {
          binding.mainBottomNavigation.menu.getItem(position).isChecked = true
        }
      })

      binding.mainBottomNavigation.setOnNavigationItemSelectedListener {
        when (it.itemId) {
          R.id.popular_movies -> currentItem = 0
          R.id.top_movies -> currentItem = 1
          R.id.popular_series -> currentItem = 2
          R.id.top_series -> currentItem = 3
        }
        true
      }
    }
    setupSearchView()
  }

  private fun setupSearchView() {
    val searchView: SearchView = findViewById(R.id.search_view)

    searchView.setOnSearchClickListener { }
    searchView.setOnCloseListener { false }

    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {

        if (!query.isNullOrEmpty())
        {
          SearchActivity.startActivityModel(searchView.context, query)
          searchView.setQuery("", false)
          searchView.clearFocus()

        }
        return false
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        return false
      }
    })
  }
}
