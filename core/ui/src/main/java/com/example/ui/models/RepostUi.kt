package com.example.ui.models

data class RepostUi(
    override val originalPostText: String,
    override val originalPostAuthor: String,
    override val repostClickAction: (UiPost) -> Unit,
    override val type: Companion.ViewType = Companion.ViewType.REPOST_UI,
    val userNameAuthor: String
) : UiPost(originalPostText, originalPostAuthor, repostClickAction, type)