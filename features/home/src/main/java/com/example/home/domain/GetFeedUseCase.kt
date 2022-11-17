package com.example.home.domain

import com.example.models.domain.Post
import kotlinx.coroutines.flow.Flow

interface GetFeedUseCase {
    operator fun invoke(): Flow<List<Post>>
}