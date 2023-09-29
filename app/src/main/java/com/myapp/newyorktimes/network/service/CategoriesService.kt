package com.myapp.newyorktimes.network.service

import com.myapp.newyorktimes.network.dto.category.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface CategoriesService {
    @GET("lists/names.json")
    suspend fun getCategories(@Query("api-key") key: String): CategoryResponse
}