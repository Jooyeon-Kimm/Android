package com.app.practice.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.practice.data.db.dao.BasketDao
import com.app.practice.data.db.dao.LikeDao
import com.app.practice.data.db.dao.PurchaseDao
import com.app.practice.data.db.entity.BasketProductEntity
import com.app.practice.data.db.entity.LikeProductEntity
import com.app.practice.data.db.entity.PurchaseProductEntity

@Database(
    entities = [
        BasketProductEntity::class,
        PurchaseProductEntity::class,
        LikeProductEntity::class,
    ],
    version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        val DB_NAME = "ApplicationDatabase.db"
    }

    abstract fun purchaseDao(): PurchaseDao
    abstract fun basketDao(): BasketDao
    abstract fun likeDao(): LikeDao

}