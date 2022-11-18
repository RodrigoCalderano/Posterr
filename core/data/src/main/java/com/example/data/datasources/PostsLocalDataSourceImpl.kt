package com.example.data.datasources

import com.example.data.dao.PostsDao
import com.example.data.mappers.toPostEntity
import com.example.data.mappers.toPostMapper
import com.example.domain.time.TimeStamp
import com.example.models.domain.Post
import kotlinx.coroutines.flow.map

internal class PostsLocalDataSourceImpl(
    private val postsDao: PostsDao,
    private val timeStamp: TimeStamp
) : PostsLocalDataSource {
    override fun retrieveAllPosts() = postsDao.retrieveAll().map { it.toPostMapper() }

    override fun retrieveAllPostsFromUser(userName: String) =
        postsDao.retrieveAllFromUser(userName).map { it.toPostMapper() }

    override fun insert(post: List<Post>) {
        postsDao.insertOrUpdate(post.toPostEntity(timeStamp.getTimeStamp()))
    }

    override fun getTimeOfFifthLastPost(userName: String): Long? {
        return postsDao.retrieveFiveLastPostFromUser(userName)?.let {
            if (it.size != FIVE) null else it.last().timeStamp
        }
    }

    companion object {
        private const val FIVE = 5
    }
}