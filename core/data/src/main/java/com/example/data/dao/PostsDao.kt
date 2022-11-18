package com.example.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.models.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao : BaseDao<PostEntity> {
    @Query("SELECT * FROM post_table order by id desc")
    fun retrieveAll(): Flow<List<PostEntity>>

    @Query("DELETE FROM post_table")
    fun deleteAll()

    @Query("SELECT * FROM post_table WHERE userNameAuthor=:userName order by id desc LIMIT 5")
    fun retrieveFiveLastPostFromUser(userName: String): List<PostEntity>?

    @Query("SELECT * FROM post_table WHERE userNameAuthor=:userName order by id")
    fun retrieveAllFromUser(userName: String): Flow<List<PostEntity>>
}