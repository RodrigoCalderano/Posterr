package com.example.data.datasources

import com.example.data.dao.PostsDao
import com.example.data.mappers.toPostEntity
import com.example.data.mappers.toPostMapper
import com.example.models.domain.Post
import kotlinx.coroutines.flow.map

internal class PostsLocalDataSourceImpl(private val postsDao: PostsDao) : PostsLocalDataSource {
    override fun retrieveAllPosts() = postsDao.retrieveAll().map { it.toPostMapper() }

    override fun insert(post: List<Post>) {
        postsDao.insertOrUpdate(post.toPostEntity())
    }
}