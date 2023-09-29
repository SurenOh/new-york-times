package com.myapp.newyorktimes.datastorage

import com.myapp.newyorktimes.mapper.book.BookDtoMapper
import com.myapp.newyorktimes.mapper.category.CategoryDtoMapper
import com.myapp.newyorktimes.model.BookModel
import com.myapp.newyorktimes.model.CategoryModel
import com.myapp.newyorktimes.network.ApiResult
import com.myapp.newyorktimes.repository.NetworkRepository

class NetworkDataStorage(
    private val repository: NetworkRepository,
    private val categoryDtoMapper: CategoryDtoMapper,
    private val bookMapper: BookDtoMapper
) {
    suspend fun getCategories(): ArrayList<CategoryModel>? {
        return when (val response = repository.getCategories()) {
            is ApiResult.Success -> categoryDtoMapper.getListModelsFromDto(response.response.results)
            is ApiResult.Error -> null
        }
    }

    suspend fun getBooks(lists: String): ArrayList<BookModel>? {
        return try {
            when (val response = repository.getBooks(lists)) {
                is ApiResult.Success -> bookMapper.getListModelsFromDto(response.response.results.books, response.response.results.listName)
                is ApiResult.Error -> null
            }
        } catch (e: Exception) {
            null
        }
    }
}