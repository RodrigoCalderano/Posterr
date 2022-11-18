package com.example.data.network

import com.example.data.models.PostResponse
import retrofit2.Call
import retrofit2.http.GET

interface PostsApi {

    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>
}