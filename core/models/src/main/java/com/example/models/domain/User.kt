package com.example.models.domain

data class User(
    val userName: String,
    val profileDataJoined: String,
    val profileOriginalPosts: Int,
    val profileReposts: Int,
    val profileQuotePosts: Int
)