package com.example.data.mappers

import com.example.data.models.UserEntity
import com.example.models.domain.User

internal fun UserEntity.toUser() = User(
    userName = userName,
    profileDataJoined = profileDataJoined,
    profileOriginalPosts = profileOriginalPosts,
    profileReposts = profileReposts,
    profileQuotePosts = profileQuotePosts
)