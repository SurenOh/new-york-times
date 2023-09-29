package com.myapp.newyorktimes.network.dto.category

data class CategoryResponse (
    val status: String,
    val results: List<CategoryDto>
)