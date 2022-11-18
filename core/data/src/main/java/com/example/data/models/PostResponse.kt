package com.example.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostResponse(
    @Json(name = "id")
    val id: Long = 0,
    @Json(name = "originalPostText")
    val originalPostText: String,
    @Json(name = "originalPostAuthor")
    val originalPostAuthor: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "userNameAuthor")
    val userNameAuthor: String,
    @Json(name = "additionalQuoteText")
    val additionalQuoteText: String?,
)