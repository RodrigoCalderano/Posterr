package com.example.data.datasources

import com.example.data.models.PostResponse
import com.example.data.network.NetworkResult
import com.example.data.network.PostsApi
import com.example.data.network.RetrofitRunner

internal class PostsRemoteDataSourceImpl(
    private val postsApi: PostsApi,
    private val retrofitRunner: RetrofitRunner
) : PostsRemoteDataSource {
    override suspend fun retrieveAllPosts(): NetworkResult<List<PostResponse>> {
        return retrofitRunner.execute(postsApi.getPosts())
    }
}