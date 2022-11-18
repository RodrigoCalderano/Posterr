package com.example.data.datasources

import com.example.data.dao.UserDao
import com.example.data.mappers.toUser
import com.example.data.mappers.toUserEntity
import com.example.models.domain.User

internal class UserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {

    override suspend fun retrieveUser(): User {
        return userDao.retrieveUser().toUser()
    }

    override fun updateUser(user: User) {
        userDao.updateUser(
            profileOriginalPosts = user.profileOriginalPosts,
            profileReposts = user.profileReposts,
            profileQuotePosts = user.profileQuotePosts
        )
    }

    override fun createUser(user: User) {
        userDao.insertOrUpdate(user.toUserEntity())
    }
}