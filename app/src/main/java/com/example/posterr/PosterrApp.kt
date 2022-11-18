package com.example.posterr

import android.app.Application
import com.example.data.di.dataModule
import com.example.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PosterrApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PosterrApp)
            modules(
                listOf(
                    homeModule,
                    dataModule,
                )
            )
        }
    }
}