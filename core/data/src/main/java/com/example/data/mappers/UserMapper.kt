package com.example.data.mappers

import com.example.data.models.UpdateUserBody
import com.example.data.models.UserEntity
import com.example.data.models.UserResponse
import com.example.models.domain.User

internal fun UserEntity.toUser() = User(
    userName = userName,
    profileDataJoined = profileDataJoined,
    profileOriginalPosts = profileOriginalPosts,
    profileReposts = profileReposts,
    profileQuotePosts = profileQuotePosts
)

internal fun User.toUserEntity() = UserEntity(
    id = 0,
    userName = userName,
    profileDataJoined = profileDataJoined,
    profileOriginalPosts = profileOriginalPosts,
    profileReposts = profileReposts,
    profileQuotePosts = profileQuotePosts
)

internal fun UserResponse.toUser() = User(
    userName = userName,
    profileDataJoined = profileDataJoined,
    profileOriginalPosts = profileOriginalPosts,
    profileReposts = profileReposts,
    profileQuotePosts = profileQuotePosts
)

internal fun User.toUpdateUserBody() = UpdateUserBody(
    profileOriginalPosts = profileOriginalPosts,
    profileReposts = profileReposts,
    profileQuotePosts = profileQuotePosts
)