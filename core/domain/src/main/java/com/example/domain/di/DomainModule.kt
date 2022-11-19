package com.example.domain.di

import com.example.domain.time.TimeStamp
import com.example.domain.usecases.AddPostUseCase
import com.example.domain.usecases.AddPostUseCaseImpl
import com.example.domain.usecases.GetUserUseCase
import com.example.domain.usecases.GetUserUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<AddPostUseCase> { AddPostUseCaseImpl(get(), get(), get()) }
    factory<GetUserUseCase> { GetUserUseCaseImpl(get()) }
    single { TimeStamp() }
}
