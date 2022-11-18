package com.example.domain.usecases

import com.example.domain.repointerfaces.UserRepository
import com.example.models.domain.User

internal class GetUserUseCaseImpl(private val userRepository: UserRepository) : GetUserUseCase {

    override suspend fun invoke(): User {
        return userRepository.getUser()
    }
}