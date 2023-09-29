package com.myapp.newyorktimes.network.service

import com.myapp.newyorktimes.network.dto.book.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksService {
    @GET("lists/current/{lists}.json")
    suspend fun getBooks(
        @Path("lists") lists: String,
        @Query("api-key") key: String
    ): BookResponse
}