package com.example.domain.usecases

import com.example.domain.repointerfaces.PostsRepository
import com.example.models.domain.Post
import com.example.models.domain.Post.Companion.PostType.ORIGINAL_POST

internal class AddPostUseCaseImpl(
    private val getUserUseCase: GetUserUseCase,
    private val postsRepository: PostsRepository
) : AddPostUseCase {
    override suspend operator fun invoke(post: Post) {
        val user = getUserUseCase.invoke()
        println("rodrigo $user")
        val originalPostAuthor = if (post.type == ORIGINAL_POST)
            user.userName else post.originalPostAuthor

        postsRepository.insertPosts(
            listOf(
                post.copy(
                    originalPostAuthor = originalPostAuthor,
                    userNameAuthor = user.userName
                )
            )
        )
    }
}