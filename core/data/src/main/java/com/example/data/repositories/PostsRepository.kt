package com.example.data.repositories

import com.example.models.domain.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getAllPosts(): Flow<List<Post>>
}