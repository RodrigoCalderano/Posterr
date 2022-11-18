package com.example.data.datasources

import com.example.models.domain.Post
import kotlinx.coroutines.flow.Flow

internal class PostsRemoteDataSourceImpl : PostsRemoteDataSource {
    override fun retrieveAllPosts(): Flow<List<Post>> {
        TODO("Not yet implemented")
    }
}