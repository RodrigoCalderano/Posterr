package com.example.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(objs: List<T>)

    @Update
    fun update(obj: T): Int

    @Delete
    fun delete(objs: List<T>)

    @Delete
    fun delete(obj: T): Int
}