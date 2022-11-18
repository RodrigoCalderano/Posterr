package com.example.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "id")
    val id: Long = 0,
    @Json(name = "userName")
    val userName: String,
    @Json(name = "profileDataJoined")
    val profileDataJoined: String,
    @Json(name = "profileOriginalPosts")
    val profileOriginalPosts: Int,
    @Json(name = "profileReposts")
    val profileReposts: Int,
    @Json(name = "profileQuotePosts")
    val profileQuotePosts: Int,
)
