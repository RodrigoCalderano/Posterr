package com.example.posterr.mock

import com.example.domain.repointerfaces.UserRepository
import com.example.models.domain.User

class FakeUserRepository : UserRepository {
    override fun createUser(user: User) {
    }

    override fun updateUser(user: User) {
    }

    override suspend fun getUser(): User {
        return stubUser
    }

    companion object {
        val stubUser = User(
            userName = "Mocked User Name",
            profileDataJoined = "March 23, 2021",
            profileOriginalPosts = 0,
            profileReposts = 0,
            profileQuotePosts = 0
        )
    }
}