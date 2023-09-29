package com.myapp.newyorktimes.repository

import com.myapp.newyorktimes.di.API_KEY
import com.myapp.newyorktimes.network.ApiResult
import com.myapp.newyorktimes.network.ResponseStatus
import com.myapp.newyorktimes.network.dto.book.BookResponse
import com.myapp.newyorktimes.network.dto.category.CategoryResponse
import com.myapp.newyorktimes.network.service.BooksService
import com.myapp.newyorktimes.network.service.CategoriesService

class NetworkRepository(
    private val booksService: BooksService,
    private val categoriesService: CategoriesService
) {
    suspend fun getCategories(): ApiResult<CategoryResponse> {
        return try {
            val data = categoriesService.getCategories(key = API_KEY)
            if (data.status == ResponseStatus.OK.value) ApiResult.Success(data) else ApiResult.Error()
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    suspend fun getBooks(lists: String): ApiResult<BookResponse> {
        return try {
            val data = booksService.getBooks(lists = lists, key = API_KEY)
            if (data.status == ResponseStatus.OK.value) ApiResult.Success(data) else ApiResult.Error()
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}