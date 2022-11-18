package com.example.domain.repointerfaces

import com.example.models.domain.User

interface UserRepository {
    fun createUser(user: User)
    fun updateUser(user: User)
    suspend fun getUser(): User
}