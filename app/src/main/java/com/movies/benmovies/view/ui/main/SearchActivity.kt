package com.movies.benmovies.view.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.movies.benmovies.R
import com.movies.benmovies.databinding.ActivitySearchBinding
import com.movies.benmovies.repository.SearchRepository
import com.movies.benmovies.utils.QuerySingleton
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import org.koin.android.ext.android.inject

class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
  @SuppressLint("MissingSuperCall")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initializeUI()
  }

  private fun initializeUI() {
    with(binding.searchViewpager) {
      adapter = SearchPagerAdapter(supportFragmentManager, lifecycle)
      registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) = kotlin.Unit
        override fun onPageScrolled(
          position: Int,
          positionOffset: Float,
          positionOffsetPixels: Int
        ) = kotlin.Unit

        override fun onPageSelected(position: Int) {
          binding.searchBottomNavigation.menu.getItem(position).isChecked = true
        }
      })

      binding.searchBottomNavigation.setOnNavigationItemSelectedListener {
        when (it.itemId) {
          R.id.movies -> currentItem = 0
          R.id.series -> currentItem = 1
        }
        true
      }
    }
  }

  companion object {
    private const val extraQuery = "com.movies.benmovies.QUERY"
    fun startActivityModel(context: Context?, query: String?) {

      val querySingleton : QuerySingleton = QuerySingleton.instance
      if (query != null) {
        querySingleton.query = query
      }
      context?.intentOf<SearchActivity> {
        startActivity(context)
      }
    }
  }
}
