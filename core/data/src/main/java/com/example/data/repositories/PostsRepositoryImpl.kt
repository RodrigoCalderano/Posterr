package com.example.data.repositories

import com.example.data.datasources.PostsLocalDataSource
import com.example.models.domain.Post

internal class PostsRepositoryImpl(
    private val postsLocalDataSource: PostsLocalDataSource
) : PostsRepository {
    override fun getAllPosts() = postsLocalDataSource.retrieveAllPosts()

    override fun insertPost(post: Post) = postsLocalDataSource.insert(post)
}