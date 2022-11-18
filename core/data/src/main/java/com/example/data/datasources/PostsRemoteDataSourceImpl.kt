package com.example.data.datasources

import com.example.data.mappers.toPostResponse
import com.example.data.models.PostResponse
import com.example.data.network.NetworkResult
import com.example.data.network.PostsApi
import com.example.data.network.RetrofitRunner
import com.example.models.domain.Post

internal class PostsRemoteDataSourceImpl(
    private val postsApi: PostsApi,
    private val retrofitRunner: RetrofitRunner
) : PostsRemoteDataSource {
    override suspend fun retrieveAllPosts(): NetworkResult<List<PostResponse>> {
        return retrofitRunner.execute(postsApi.getPosts())
    }

    override fun uploadPost(posts: List<Post>) {
        postsApi.uploadPost(posts.toPostResponse())
    }
}