package com.example.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.models.UserEntity

@Dao
interface UserDao : BaseDao<UserEntity> {
    @Query("SELECT * FROM user_table WHERE id=:forUserWithId")
    suspend fun retrieveUser(forUserWithId: Int = 1): UserEntity

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<UserEntity>?

    @Query(
        "UPDATE user_table SET profileOriginalPosts=:profileOriginalPosts," +
            " profileReposts=:profileReposts," +
            " profileQuotePosts=:profileQuotePosts" +
            " WHERE id=:forUserWithId"
    )
    fun updateUser(
        profileOriginalPosts: Int,
        profileReposts: Int,
        profileQuotePosts: Int,
        forUserWithId: Int = 1
    ): Int
}