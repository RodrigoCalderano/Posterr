package com.example.home.di

import com.example.home.domain.GetFeedUseCase
import com.example.home.domain.GetFeedUseCaseImpl
import com.example.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            getFeedUseCase = get(),
            addPostUseCase = get(),
        )
    }
    factory<GetFeedUseCase> { GetFeedUseCaseImpl(get()) }
}