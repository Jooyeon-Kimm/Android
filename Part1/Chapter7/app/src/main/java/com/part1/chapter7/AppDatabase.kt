package com.part1.chapter7

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class, ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // Dao 추가
    abstract fun wordDao(): WordDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        // 싱글톤을 사용하겠다는 의미
        fun getInstance(context:Context): AppDatabase? {
            if(INSTANCE==null) {
                synchronized(AppDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}