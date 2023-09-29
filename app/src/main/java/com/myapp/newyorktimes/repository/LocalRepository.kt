package com.myapp.newyorktimes.repository

import com.myapp.newyorktimes.db.MyDatabase
import com.myapp.newyorktimes.db.entity.BookEntity
import com.myapp.newyorktimes.db.entity.CategoryEntity

class LocalRepository(db: MyDatabase) {
    private val bookDao = db.getBooks()
    private val categoryDao = db.getCategories()

    fun insertBook(book: BookEntity) {
        bookDao.insertBook(book)
    }
    fun insertCategory(category: CategoryEntity) {
        categoryDao.insertCategory(category)
    }

    fun getBooks(lists: String) = bookDao.getBooks(lists)
    fun getCategories() = categoryDao.getCategories()
}