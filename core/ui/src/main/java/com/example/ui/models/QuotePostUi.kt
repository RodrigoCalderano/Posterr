package com.example.ui.models

data class QuotePostUi(
    override val originalPostText: String,
    override val originalPostAuthor: String,
    override val repostClickAction: () -> Unit,
    override val type: Companion.ViewType = Companion.ViewType.QUOTE_POST_UI,
    val userNameAuthor: String,
    val additionalQuoteText: String
) : UiPost(originalPostText, userNameAuthor, repostClickAction, type)