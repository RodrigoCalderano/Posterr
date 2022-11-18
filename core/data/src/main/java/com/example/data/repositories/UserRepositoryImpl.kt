package com.example.data.repositories

import com.example.data.datasources.UserLocalDataSource
import com.example.data.datasources.UserRemoteDataSource
import com.example.data.mappers.toUpdateUserBody
import com.example.data.mappers.toUser
import com.example.data.network.NetworkResult
import com.example.domain.repointerfaces.UserRepository
import com.example.models.domain.User

internal class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override fun createUser(user: User) {
        userLocalDataSource.createUser(user)
    }

    override fun updateUser(user: User) {
        userRemoteDataSource.updateUser(user.toUpdateUserBody())
        userLocalDataSource.updateUser(user)
    }

    override suspend fun getUser(): User {
        return with(userRemoteDataSource.retrieveUserData()) {
            when (this) {
                is NetworkResult.Success -> {
                    val user = data.toUser()
                    userLocalDataSource.createUser(user)
                    user
                }
                else -> userLocalDataSource.retrieveUser()
            }
        }
    }
}