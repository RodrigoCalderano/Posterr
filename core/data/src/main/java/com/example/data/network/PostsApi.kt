package com.example.data.network

import com.example.data.models.PostResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface PostsApi {

    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>

    @POST("posts")
    fun uploadPost(@Body postResponse: List<PostResponse>): Call<Unit>
}