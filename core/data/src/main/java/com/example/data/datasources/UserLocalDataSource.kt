package com.example.data.datasources

import com.example.models.domain.User

internal interface UserLocalDataSource {
    suspend fun retrieveUser(): User
    fun updateUser(user: User)
    fun createUser(user: User)
}