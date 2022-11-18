package com.example.data.network

import com.example.data.models.UpdateUserBody
import com.example.data.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

internal interface UserApi {

    @GET("user")
    fun getUser(): Call<UserResponse>

    @PATCH
    fun updateUser(@Body updateUserBody: UpdateUserBody): Call<Unit>
}