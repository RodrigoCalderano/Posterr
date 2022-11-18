package com.example.data.di

import androidx.room.Room
import com.example.data.dao.AppDatabase
import com.example.data.datasources.PostsLocalDataSource
import com.example.data.datasources.PostsLocalDataSourceImpl
import com.example.data.repositories.PostsRepository
import com.example.data.repositories.PostsRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single { get<AppDatabase>().postsDao() }
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "db")
            .addMigrations()
            .build()
    }

    factory<PostsRepository> { PostsRepositoryImpl(get()) }
    factory<PostsLocalDataSource> { PostsLocalDataSourceImpl(get()) }
}