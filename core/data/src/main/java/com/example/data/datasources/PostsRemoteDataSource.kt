package com.example.data.datasources

import com.example.data.models.PostResponse
import com.example.data.network.NetworkResult
import com.example.models.domain.Post

internal interface PostsRemoteDataSource {
    suspend fun retrieveAllPosts(): NetworkResult<List<PostResponse>>
    fun uploadPost(posts: List<Post>)
}