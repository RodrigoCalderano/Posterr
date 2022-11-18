package com.example.data.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.data.dao.AppDatabase
import com.example.data.datasources.PostsLocalDataSource
import com.example.data.datasources.PostsLocalDataSourceImpl
import com.example.data.datasources.PostsRemoteDataSource
import com.example.data.datasources.PostsRemoteDataSourceImpl
import com.example.data.network.PostsApi
import com.example.data.network.RetrofitProvider
import com.example.data.network.RetrofitRunner
import com.example.data.repositories.PostsRepository
import com.example.data.repositories.PostsRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    factory<PostsRepository> { PostsRepositoryImpl(get(), get()) }
    factory<PostsLocalDataSource> { PostsLocalDataSourceImpl(get()) }
    factory<PostsRemoteDataSource> { PostsRemoteDataSourceImpl(get(), get()) }

    // DB
    single { get<AppDatabase>().postsDao() }
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "db")
            .addMigrations()
            .build()
    }

    // NETWORK
    single { androidApplication().getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager }
    single { RetrofitRunner(get()) }
    single { RetrofitProvider.createService(PostsApi::class.java) }
}