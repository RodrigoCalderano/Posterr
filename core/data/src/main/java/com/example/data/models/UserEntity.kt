package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "userName")
    val userName: String,
    @ColumnInfo(name = "profileDataJoined")
    val profileDataJoined: String,
    @ColumnInfo(name = "profileOriginalPosts")
    val profileOriginalPosts: Int,
    @ColumnInfo(name = "profileReposts")
    val profileReposts: Int,
    @ColumnInfo(name = "profileQuotePosts")
    val profileQuotePosts: Int,
)