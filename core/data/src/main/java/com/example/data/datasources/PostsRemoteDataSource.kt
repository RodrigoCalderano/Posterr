package com.example.data.datasources

import com.example.data.models.PostResponse
import com.example.data.network.NetworkResult

internal interface PostsRemoteDataSource {
    suspend fun retrieveAllPosts(): NetworkResult<List<PostResponse>>
}