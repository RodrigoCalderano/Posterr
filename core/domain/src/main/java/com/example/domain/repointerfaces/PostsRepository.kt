package com.example.domain.repointerfaces

import com.example.models.domain.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getAllPosts(): Flow<List<Post>>
    fun insertPosts(posts: List<Post>)
}