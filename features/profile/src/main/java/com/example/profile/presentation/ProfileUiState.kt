package com.example.profile.presentation

import com.example.models.domain.Post
import com.example.models.domain.User
import com.example.ui.models.UiPost

sealed class ProfileUiState {
    data class UpdateScreenSuccessState(val posts: List<UiPost>, val user: User) : ProfileUiState()
    data class Repost(val post: Post) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
    data class Toast(val message: String) : ProfileUiState()
    object Loading : ProfileUiState()
}