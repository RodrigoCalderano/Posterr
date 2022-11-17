package com.example.home.presentation

import com.example.models.domain.Post
import com.example.ui.models.UiPost

sealed class HomeUiState {
    data class NewPosts(val posts: List<UiPost>) : HomeUiState()
    data class Repost(val post: Post) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
    object Loading : HomeUiState()
}