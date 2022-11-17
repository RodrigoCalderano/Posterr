package com.example.models.domain

data class User(
    val userName: String,
    val joinedData: String,
    val originalPosts: Int,
    val reposts: Int,
    val quotePosts: Int
)