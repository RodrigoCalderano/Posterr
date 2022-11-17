package com.example.ui.models

sealed class UiPost(
    open val originalPostText: String,
    open val originalPostAuthor: String,
    open val repostClickAction: () -> Unit,
    open val type: ViewType
) {
    companion object {
        enum class ViewType {
            ORIGINAL_POST_UI,
            REPOST_UI,
            QUOTE_POST_UI
        }
    }
}