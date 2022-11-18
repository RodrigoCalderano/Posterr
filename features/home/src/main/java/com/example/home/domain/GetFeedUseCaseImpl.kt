package com.example.home.domain

import com.example.data.repositories.PostsRepository
import com.example.models.domain.Post
import kotlinx.coroutines.flow.Flow

internal class GetFeedUseCaseImpl(private val postsRepository: PostsRepository) : GetFeedUseCase {
    override fun invoke(): Flow<List<Post>> {
        return postsRepository.getAllPosts()
    }
}