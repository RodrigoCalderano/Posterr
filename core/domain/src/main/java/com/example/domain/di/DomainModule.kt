package com.example.domain.di

import com.example.domain.usecases.AddPostUseCase
import com.example.domain.usecases.AddPostUseCaseImpl
import com.example.domain.usecases.GetUserUseCase
import com.example.domain.usecases.GetUserUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<AddPostUseCase> { AddPostUseCaseImpl(userRepository = get(), postsRepository = get()) }
    factory<GetUserUseCase> { GetUserUseCaseImpl(get()) }
}
