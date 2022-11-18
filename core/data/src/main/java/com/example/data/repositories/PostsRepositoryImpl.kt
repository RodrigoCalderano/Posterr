package com.example.data.repositories

import com.example.data.datasources.PostsLocalDataSource
import com.example.data.datasources.PostsRemoteDataSource
import com.example.data.mappers.toPost
import com.example.data.network.NetworkResult
import com.example.models.domain.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

internal class PostsRepositoryImpl(
    private val postsLocalDataSource: PostsLocalDataSource,
    private val postsRemoteDataSource: PostsRemoteDataSource
) : PostsRepository {
    override fun getAllPosts(): Flow<List<Post>> {
        return flow {
            with(postsRemoteDataSource.retrieveAllPosts()) {
                when (this) {
                    is NetworkResult.Success -> {
                        val posts = data.toPost()
                        postsLocalDataSource.insert(posts)
                        emit(data.toPost())
                    }
                    else -> emitAll(postsLocalDataSource.retrieveAllPosts())
                }
            }
        }
    }

    override fun insertPosts(posts: List<Post>) {
        postsRemoteDataSource.uploadPost(posts) // Todo handle upload failure
        postsLocalDataSource.insert(posts)
    }
}