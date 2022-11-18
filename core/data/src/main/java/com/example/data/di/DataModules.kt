package com.example.data.di

import com.example.data.repositories.PostsRepository
import com.example.data.repositories.PostsRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    factory<PostsRepository> { PostsRepositoryImpl() }
}