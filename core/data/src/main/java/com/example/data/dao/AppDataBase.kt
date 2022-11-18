package com.example.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.PostEntity

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        PostEntity::class
    ]
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}