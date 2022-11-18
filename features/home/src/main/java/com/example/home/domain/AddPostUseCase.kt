package com.example.home.domain

import com.example.models.domain.Post

internal interface AddPostUseCase {
    operator fun invoke(post: Post)
}