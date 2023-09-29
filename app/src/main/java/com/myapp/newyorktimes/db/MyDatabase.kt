package com.myapp.newyorktimes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapp.newyorktimes.db.dao.BookDao
import com.myapp.newyorktimes.db.dao.CategoryDao
import com.myapp.newyorktimes.db.entity.BookEntity
import com.myapp.newyorktimes.db.entity.CategoryEntity

@Database(entities = [CategoryEntity::class, BookEntity::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getBooks() : BookDao
    abstract fun getCategories() : CategoryDao
}