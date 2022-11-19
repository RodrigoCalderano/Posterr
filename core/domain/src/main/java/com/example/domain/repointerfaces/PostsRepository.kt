package com.example.domain.repointerfaces

import com.example.models.domain.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getAllPosts(): Flow<List<Post>>
    fun getAllPostsFromUser(userName: String): Flow<List<Post>>
    fun getTimeOfFifthLastPost(userName: String): Long?
    fun insertPosts(posts: List<Post>)
}