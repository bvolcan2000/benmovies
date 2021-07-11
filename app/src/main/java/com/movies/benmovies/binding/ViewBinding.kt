
package com.movies.benmovies.binding

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.movies.benmovies.api.Api
import com.movies.benmovies.extension.requestGlideListener
import com.movies.benmovies.extension.setQuery
import com.movies.benmovies.extension.visible
import com.movies.benmovies.models.entity.Movie
import com.movies.benmovies.models.entity.TrendingMovie
import com.movies.benmovies.models.entity.TrendingTv
import com.movies.benmovies.models.entity.Tv
import com.movies.benmovies.view.adapter.MovieListAdapter
import com.movies.benmovies.view.adapter.TvListAdapter
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullOrEmpty

object ViewBinding {

  @JvmStatic
  @BindingAdapter("toast")
  fun bindToast(view: View, text: LiveData<String>) {
    text.value.whatIfNotNull {
      Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
    }
  }

  @JvmStatic
  @BindingAdapter("loadPaletteImage", "loadPaletteTarget")
  fun bindLoadImage(view: AppCompatImageView, url: String, targetView: View) {
    Glide.with(view)
      .load(url)
      .listener(
        GlidePalette.with(url)
          .use(BitmapPalette.Profile.VIBRANT)
          .intoBackground(targetView)
          .crossfade(true)
      )
      .into(view)
  }

  @JvmStatic
  @BindingAdapter("visibilityByResource")
  fun bindVisibilityByResource(view: View, anyList: List<Any>?) {
    anyList.whatIfNotNullOrEmpty {
      view.visible()
    }
  }

  @JvmStatic
  @SuppressLint("SetTextI18n")
  @BindingAdapter("bindReleaseDate")
  fun bindReleaseDate(view: TextView, movie: Movie) {
    view.text = "Release Date : ${movie.release_date}"
  }

  @JvmStatic
  @SuppressLint("SetTextI18n")
  @BindingAdapter("bindReleaseDate")
  fun bindReleaseDate(view: TextView, movie: TrendingMovie) {
    view.text = "Release Date : ${movie.release_date}"
  }

  @JvmStatic
  @SuppressLint("SetTextI18n")
  @BindingAdapter("bindAirDate")
  fun bindAirDate(view: TextView, tv: Tv) {
    view.text = "First Air Date : ${tv.first_air_date}"
  }

  @JvmStatic
  @SuppressLint("SetTextI18n")
  @BindingAdapter("bindAirDate")
  fun bindAirDate(view: TextView, tv: TrendingTv) {
    view.text = "First Air Date : ${tv.first_air_date}"
  }

  @JvmStatic
  @BindingAdapter("bindBackDrop")
  fun bindBackDrop(view: ImageView, movie: Movie) {
    movie.backdrop_path.whatIfNotNull(
      whatIf = {
        Glide.with(view.context).load(Api.getBackdropPath(it))
          .listener(view.requestGlideListener())
          .into(view)
      },
      whatIfNot = {
        Glide.with(view.context).load(Api.getBackdropPath(movie.poster_path))
          .listener(view.requestGlideListener())
          .into(view)
      }
    )
  }

  @JvmStatic
  @BindingAdapter("bindBackDrop")
  fun bindBackDrop(view: ImageView, movie: TrendingMovie) {
    movie.backdrop_path.whatIfNotNull(
      whatIf = {
        Glide.with(view.context).load(Api.getBackdropPath(it))
          .listener(view.requestGlideListener())
          .into(view)
      },
      whatIfNot = {
        Glide.with(view.context).load(Api.getBackdropPath(movie.poster_path))
          .listener(view.requestGlideListener())
          .into(view)
      }
    )
  }

  @JvmStatic
  @BindingAdapter("bindBackDrop")
  fun bindBackDrop(view: ImageView, tv: Tv) {
    tv.backdrop_path.whatIfNotNull(
      whatIf = {
        Glide.with(view.context).load(Api.getBackdropPath(it))
          .listener(view.requestGlideListener())
          .into(view)
      },
      whatIfNot = {
        Glide.with(view.context).load(Api.getBackdropPath(tv.poster_path))
          .listener(view.requestGlideListener())
          .into(view)
      }
    )
  }

  @JvmStatic
  @BindingAdapter("bindBackDrop")
  fun bindBackDrop(view: ImageView, tv: TrendingTv) {
    tv.backdrop_path.whatIfNotNull(
      whatIf = {
        Glide.with(view.context).load(Api.getBackdropPath(it))
          .listener(view.requestGlideListener())
          .into(view)
      },
      whatIfNot = {
        Glide.with(view.context).load(Api.getBackdropPath(tv.poster_path))
          .listener(view.requestGlideListener())
          .into(view)
      }
    )
  }
}
