package com.myapp.newyorktimes.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapp.newyorktimes.db.entity.BookEntity

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: BookEntity)

    @Query("SELECT * FROM BookEntity WHERE listName = :lists")
    fun getBooks(lists: String) : List<BookEntity>
}