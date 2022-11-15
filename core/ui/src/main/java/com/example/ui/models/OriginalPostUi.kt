package com.example.ui.models

data class OriginalPostUi(
    override val originalPostText: String,
    val userNameAuthor: String,
    override val type: Companion.ViewType = Companion.ViewType.ORIGINAL_POST_UI
) : UiPosts(originalPostText, userNameAuthor, type)