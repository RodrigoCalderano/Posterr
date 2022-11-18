package com.example.data.datasources

import com.example.models.domain.Post
import kotlinx.coroutines.flow.Flow

internal interface PostsLocalDataSource {
    fun retrieveAllPosts(): Flow<List<Post>>
    fun insert(post: Post)
}