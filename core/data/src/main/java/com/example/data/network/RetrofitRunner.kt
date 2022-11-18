package com.example.data.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.awaitResponse

internal class RetrofitRunner(
    private val connectivityManager: ConnectivityManager
) {
    private suspend fun <F, T> execute(
        call: Call<F>,
        mapper: (Response<F>) -> T
    ): NetworkResult<T> {
        if (!isConnected()) {
            return NetworkResult.ConnectionFailure
        }

        return try {
            val response = call.awaitResponse()
            if (response.isSuccessful) {
                NetworkResult.Success(mapper(response))
            } else {
                TODO("Convert other possible responses")
            }
        } catch (e: Exception) {
            NetworkResult.RequestFailure(e)
        }
    }

    suspend fun <F> execute(call: Call<F>) = execute(call, mapper = { it.bodyOrThrow() })

    private fun isConnected(): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }

    private fun <T> Response<T>.bodyOrThrow(): T {
        if (!isSuccessful) throw HttpException(this)
        body()?.let {
            return it
        } ?: throw HttpException(this)
    }
}
