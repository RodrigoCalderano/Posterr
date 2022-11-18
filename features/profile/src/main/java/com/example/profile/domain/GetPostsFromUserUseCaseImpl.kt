package com.example.profile.domain

import com.example.domain.repointerfaces.PostsRepository
import com.example.models.domain.Post
import kotlinx.coroutines.flow.Flow

internal class GetPostsFromUserUseCaseImpl(
    private val postsRepository: PostsRepository
) : GetPostsFromUserUseCase {

    override fun invoke(userName: String): Flow<List<Post>> {
        return postsRepository.getAllPostsFromUser(userName)
    }
}