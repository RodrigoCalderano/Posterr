package com.example.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.models.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface UserDao : BaseDao<UserEntity> {
    @Query("SELECT * FROM user_table WHERE id=:forUserWithId")
    fun retrieveUser(forUserWithId: Int = 0): Flow<UserEntity>

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
        forUserWithId: Int = 0
    ): Int
}