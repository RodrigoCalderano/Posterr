package com.example.data.network

internal sealed class NetworkResult<out T> {

    data class Success<out T>(val data: T) : NetworkResult<T>()

    abstract class Failure : NetworkResult<Nothing>()

    object ConnectionFailure : Failure()

    data class RequestFailure(val cause: Throwable) : Failure()
}