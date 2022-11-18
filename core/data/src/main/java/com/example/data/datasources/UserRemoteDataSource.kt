package com.example.data.datasources

import com.example.data.models.UpdateUserBody
import com.example.data.models.UserResponse
import com.example.data.network.NetworkResult

internal interface UserRemoteDataSource {
    suspend fun retrieveUserData(): NetworkResult<UserResponse>
    fun updateUser(user: UpdateUserBody)
}