package com.movies.benmovies

import android.app.Application
import com.facebook.stetho.Stetho
import com.skydoves.sandwich.SandwichInitializer
import com.movies.benmovies.di.networkModule
import com.movies.benmovies.di.persistenceModule
import com.movies.benmovies.di.repositoryModule
import com.movies.benmovies.di.viewModelModule
import com.movies.benmovies.operator.GlobalResponseOperator
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class MoviesApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@MoviesApplication)
      modules(networkModule)
      modules(persistenceModule)
      modules(repositoryModule)
      modules(viewModelModule)
    }

    // initialize global sandwich operator
    SandwichInitializer.sandwichOperator = GlobalResponseOperator<Any>(this)


    Stetho.initializeWithDefaults(this)
  }
}
