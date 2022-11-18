package com.example.data.network

import com.example.data.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {

    private var retrofit: Retrofit = buildRetrofit()

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .client(OkHttpClient.Builder().build())
        .build()

    fun <T> createService(service: Class<T>): T = retrofit.create(service)
}