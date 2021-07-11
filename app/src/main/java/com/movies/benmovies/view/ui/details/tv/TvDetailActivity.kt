package com.movies.benmovies.view.ui.details.tv

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.movies.benmovies.R
import com.movies.benmovies.databinding.ActivityTvDetailBinding
import com.movies.benmovies.models.entity.Tv
import com.movies.benmovies.view.adapter.ReviewListAdapter
import com.movies.benmovies.view.adapter.VideoListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class TvDetailActivity :
  BindingActivity<ActivityTvDetailBinding>(R.layout.activity_tv_detail) {

  private val vm: TvDetailViewModel by viewModel()
  private val intentTv: Tv by bundleNonNull(TV_ID)

  @SuppressLint("MissingSuperCall")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding {
      activity = this@TvDetailActivity
      lifecycleOwner = this@TvDetailActivity
      viewModel = vm.apply { postTvId(intentTv.id) }
      tv = intentTv
      videoAdapter = VideoListAdapter()
      reviewAdapter = ReviewListAdapter()
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) onBackPressed()
    return false
  }

  companion object {
    private const val TV_ID = "tv"
    fun startActivityModel(context: Context?, tv: Tv?) {
      context?.intentOf<TvDetailActivity> {
        putExtra(TV_ID to tv)
        startActivity(context)
      }
    }
  }
}
