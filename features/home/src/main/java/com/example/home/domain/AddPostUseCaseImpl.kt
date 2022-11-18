package com.example.home.domain

import com.example.data.repositories.PostsRepository
import com.example.models.domain.Post

internal class AddPostUseCaseImpl(private val postsRepository: PostsRepository) : AddPostUseCase {
    override operator fun invoke(post: Post) {
        postsRepository.insertPosts(listOf(post))
    }
}