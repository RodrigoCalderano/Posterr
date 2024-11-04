package com.example.posterr.mockdi

import com.example.domain.repointerfaces.PostsRepository
import com.example.domain.repointerfaces.UserRepository
import com.example.posterr.mock.FakePostsRepository
import com.example.posterr.mock.FakeUserRepository
import org.koin.dsl.module

val fakeRepoModule = module(override = true) {
    single<UserRepository> { FakeUserRepository() }
    single<PostsRepository> { FakePostsRepository() }
}