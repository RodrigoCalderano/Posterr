package com.example.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.PostEntity
import com.example.data.models.UserEntity

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        PostEntity::class,
        UserEntity::class
    ]
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun userDao(): UserDao
}