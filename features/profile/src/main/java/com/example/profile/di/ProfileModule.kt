package com.example.profile.di

import com.example.profile.domain.GetPostsFromUserUseCase
import com.example.profile.domain.GetPostsFromUserUseCaseImpl
import com.example.profile.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel {
        ProfileViewModel(
            getPostsFromUserUseCase = get(),
            addPostUseCase = get(),
            getUserUseCase = get(),
        )
    }
    factory<GetPostsFromUserUseCase> { GetPostsFromUserUseCaseImpl(get()) }
}