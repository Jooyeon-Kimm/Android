package com.part1.chapter7

import androidx.room.*

@Dao
interface WordDao {
    
    // 최신 추가된 순서대로 위에 보이도록
    @Query("SELECT * from word ORDER BY id DESC")
    fun getAll(): List<Word>
    
    // 가장 최신 것 하나만, ID 값으로 하나, TEXT 로 하나만 받고 싶으면?
    // id 값으로 하나 받아오는 쿼리 ( LIMIT )
    @Query("SELECT * from word ORDER BY id DESC LIMIT 1")
    fun getLatestWord() : Word

    @Insert
    fun insert(word: Word)
    
    @Delete
    fun delete(word: Word)
    
    @Update
    fun update(word: Word)
    
}