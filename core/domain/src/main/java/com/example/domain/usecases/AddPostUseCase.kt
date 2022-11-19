package com.example.domain.usecases

import com.example.models.domain.Post

interface AddPostUseCase {
    suspend operator fun invoke(post: Post): AddPostResult
}

enum class AddPostResult {
    SUCCEEDED,
    EXCEEDED_DAILY_LIMIT
}