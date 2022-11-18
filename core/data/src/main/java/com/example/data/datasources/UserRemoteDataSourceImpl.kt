package com.example.data.datasources

import com.example.data.models.UpdateUserBody
import com.example.data.models.UserResponse
import com.example.data.network.NetworkResult
import com.example.data.network.RetrofitRunner
import com.example.data.network.UserApi

internal class UserRemoteDataSourceImpl(
    private val userApi: UserApi,
    private val retrofitRunner: RetrofitRunner
) : UserRemoteDataSource {
    override suspend fun retrieveUserData(): NetworkResult<UserResponse> {
        return retrofitRunner.execute(userApi.getUser())
    }

    override fun updateUser(user: UpdateUserBody) {
        userApi.updateUser(user)
    }
}