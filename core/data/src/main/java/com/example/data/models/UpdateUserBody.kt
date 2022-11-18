package com.example.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UpdateUserBody(
    @Json(name = "profileOriginalPosts")
    val profileOriginalPosts: Int,
    @Json(name = "profileReposts")
    val profileReposts: Int,
    @Json(name = "profileQuotePosts")
    val profileQuotePosts: Int,
)