package com.example.ui.models

data class QuotePostUi(
    override val originalPostText: String,
    override val originalPostAuthor: String,
    val additionalQuoteText: String,
    val userNameAuthor: String,
    override val type: Companion.ViewType = Companion.ViewType.QUOTE_POST_UI
) : UiPosts(originalPostText, userNameAuthor, type)