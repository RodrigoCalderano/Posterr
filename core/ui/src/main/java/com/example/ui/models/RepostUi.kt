package com.example.ui.models

data class RepostUi(
    override val originalPostText: String,
    override val originalPostAuthor: String,
    val userNameAuthor: String,
    override val type: Companion.ViewType = Companion.ViewType.REPOST_UI
) : UiPosts(originalPostText, originalPostAuthor, type)