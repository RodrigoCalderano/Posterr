package com.example.data.datasources

import com.example.data.dao.UserDao
import com.example.data.mappers.toUser
import com.example.models.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class UserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {

    override fun retrieveUser(): Flow<User> {
        return userDao.retrieveUser().map { it.toUser() }
    }

    override fun updateUser(user: User) {
        userDao.updateUser(
            profileOriginalPosts = user.profileOriginalPosts,
            profileReposts = user.profileReposts,
            profileQuotePosts = user.profileQuotePosts
        )
    }
}