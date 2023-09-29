package com.myapp.newyorktimes.network.dto.book

data class BookResponse (
    val status: String,
    val results: BooksResultDto
)