package com.app.practice.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.practice.data.db.entity.LikeProductEntity

@Dao
interface LikeDao {
    @Query("SELECT * FROM liked")
    suspend fun getAll() : List<LikeProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: LikeProductEntity)

    @Query("DELETE FROM liked WHERE productId=:id")
    suspend fun delete(id: String)
}