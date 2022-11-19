package com.example.posterr.mockdi

import com.example.domain.repointerfaces.PostsRepository
import com.example.domain.repointerfaces.UserRepository
import com.example.posterr.mock.MockPostsRepository
import com.example.posterr.mock.MockUserRepository
import org.koin.dsl.module

val mockedModule = module(override = true) {
    single<UserRepository> { MockUserRepository() }
    single<PostsRepository> { MockPostsRepository() }
}