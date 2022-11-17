package com.example.ui.models

data class OriginalPostUi(
    override val originalPostText: String,
    override val originalPostAuthor: String,
    override val repostClickAction: () -> Unit,
    override val type: Companion.ViewType = Companion.ViewType.ORIGINAL_POST_UI
) : UiPost(originalPostText, originalPostAuthor, repostClickAction, type)