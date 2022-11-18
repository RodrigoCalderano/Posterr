package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "originalPostText")
    val originalPostText: String,
    @ColumnInfo(name = "originalPostAuthor")
    val originalPostAuthor: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "userNameAuthor")
    val userNameAuthor: String?,
    @ColumnInfo(name = "additionalQuoteText")
    val additionalQuoteText: String?,
)