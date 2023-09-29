package com.myapp.newyorktimes.datastorage

import com.myapp.newyorktimes.mapper.book.BookEntityMapper
import com.myapp.newyorktimes.mapper.category.CategoryEntityMapper
import com.myapp.newyorktimes.model.BookModel
import com.myapp.newyorktimes.model.CategoryModel
import com.myapp.newyorktimes.repository.LocalRepository

class LocalDataStorage(
    private val repository: LocalRepository,
    private val categoryEntityMapper: CategoryEntityMapper,
    private val bookEntityMapper: BookEntityMapper
) {
    fun insertBooks(bookList: List<BookModel>) {
        bookList.forEach { repository.insertBook(bookEntityMapper.getEntityFromModel(it)) }
    }
    fun insertCategories(categoryList: List<CategoryModel>) {
        categoryList.forEach { repository.insertCategory(categoryEntityMapper.getEntityFromModel(it)) }
    }

    fun getBooks(lists: String) = bookEntityMapper.getListModelsFromEntities(repository.getBooks(lists))
    fun getCategories() = categoryEntityMapper.getListModelsFromEntities(repository.getCategories())
}