package com.example.models.domain

data class Post(
    val originalPostText: String,
    val originalPostAuthor: String,
    val type: PostType,
    val userNameAuthor: String,
    val additionalQuoteText: String? = null
) {
    companion object {
        enum class PostType(val id: String) {
            ORIGINAL_POST("ORIGINAL_POST"),
            REPOST("REPOST"),
            QUOTE_POST("QUOTE_POST")
        }

        fun getTypeFromId(id: String): PostType {
            return when (id) {
                PostType.REPOST.id -> PostType.REPOST
                PostType.QUOTE_POST.id -> PostType.QUOTE_POST
                else -> PostType.ORIGINAL_POST
            }
        }
    }
}