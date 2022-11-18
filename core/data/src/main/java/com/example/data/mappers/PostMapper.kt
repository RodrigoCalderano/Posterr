package com.example.data.mappers

import com.example.data.models.PostEntity
import com.example.data.models.PostResponse
import com.example.models.domain.Post
import com.example.models.domain.Post.Companion.getTypeFromId

internal fun List<PostEntity>.toPostMapper() = map {
    Post(
        originalPostText = it.originalPostText,
        originalPostAuthor = it.originalPostAuthor,
        type = getTypeFromId(it.type),
        userNameAuthor = it.userNameAuthor,
        additionalQuoteText = it.additionalQuoteText,
    )
}

internal fun List<PostResponse>.toPost() = map {
    Post(
        originalPostText = it.originalPostText,
        originalPostAuthor = it.originalPostAuthor,
        type = getTypeFromId(it.type),
        userNameAuthor = it.userNameAuthor,
        additionalQuoteText = it.additionalQuoteText,
    )
}

internal fun List<Post>.toPostEntity(timeStamp: Long) = map {
    PostEntity(
        originalPostText = it.originalPostText,
        originalPostAuthor = it.originalPostAuthor,
        type = it.type.id,
        userNameAuthor = it.userNameAuthor,
        additionalQuoteText = it.additionalQuoteText,
        timeStamp = timeStamp
    )
}

internal fun List<Post>.toPostResponse() = map {
    PostResponse(
        originalPostText = it.originalPostText,
        originalPostAuthor = it.originalPostAuthor,
        type = it.type.id,
        userNameAuthor = it.userNameAuthor,
        additionalQuoteText = it.additionalQuoteText,
    )
}