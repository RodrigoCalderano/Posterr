package com.example.profile.domain

import com.example.models.domain.Post
import kotlinx.coroutines.flow.Flow

interface GetPostsFromUserUseCase {
    operator fun invoke(userName: String): Flow<List<Post>>
}