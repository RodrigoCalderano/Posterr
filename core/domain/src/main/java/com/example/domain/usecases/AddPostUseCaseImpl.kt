package com.example.domain.usecases

import com.example.domain.repointerfaces.PostsRepository
import com.example.domain.repointerfaces.UserRepository
import com.example.models.domain.Post
import com.example.models.domain.Post.Companion.PostType.ORIGINAL_POST
import com.example.models.domain.Post.Companion.PostType.QUOTE_POST
import com.example.models.domain.Post.Companion.PostType.REPOST
import com.example.models.domain.User

internal class AddPostUseCaseImpl(
    private val userRepository: UserRepository,
    private val postsRepository: PostsRepository
) : AddPostUseCase {
    override suspend operator fun invoke(post: Post) {
        val user = userRepository.getUser()

        println("rodrigo $user")

        updateUser(user, post)
        insertPosts(post, user)
    }

    private fun insertPosts(
        post: Post,
        user: User
    ) {
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

    private fun updateUser(user: User, post: Post) {
        val profileOriginalPosts =
            user.profileOriginalPosts + if (post.type == ORIGINAL_POST) ONE else ZERO
        val profileReposts =
            user.profileReposts + if (post.type == REPOST) ONE else ZERO
        val profileQuotePosts: Int =
            user.profileQuotePosts + if (post.type == QUOTE_POST) ONE else ZERO

        userRepository.updateUser(
            user.copy(
                profileOriginalPosts = profileOriginalPosts,
                profileReposts = profileReposts,
                profileQuotePosts = profileQuotePosts,
            )
        )
    }

    companion object {
        private const val ONE = 1
        private const val ZERO = 0
    }
}