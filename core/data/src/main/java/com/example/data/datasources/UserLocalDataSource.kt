package com.example.data.datasources

import com.example.models.domain.User
import kotlinx.coroutines.flow.Flow

internal interface UserLocalDataSource {
    fun retrieveUser(): Flow<User>
    fun updateUser(user: User)
}