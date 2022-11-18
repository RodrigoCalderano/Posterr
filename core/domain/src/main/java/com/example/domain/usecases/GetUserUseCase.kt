package com.example.domain.usecases

import com.example.models.domain.User

interface GetUserUseCase {
    suspend operator fun invoke(): User
}