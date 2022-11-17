package com.example.models.domain

data class Post(
    val originalPostText: String,
    val originalPostAuthor: String,
    val type: PostType,
    val userNameAuthor: String? = null,
    val additionalQuoteText: String? = null
) {
    companion object {
        enum class PostType(id: String) {
            ORIGINAL_POST("ORIGINAL_POST"),
            REPOST("REPOST"),
            QUOTE_POST("QUOTE_POST")
        }
    }
}