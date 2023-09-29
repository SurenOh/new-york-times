package com.myapp.newyorktimes.network.dto.book

import com.google.gson.annotations.SerializedName

data class BooksResultDto(
    @SerializedName("list_name_encoded")
    val listName: String,
    val books: List<BookDto>
)
