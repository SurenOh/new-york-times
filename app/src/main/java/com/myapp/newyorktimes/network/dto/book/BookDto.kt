package com.myapp.newyorktimes.network.dto.book

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("title")
    val name: String,
    val description: String,
    val author: String,
    val publisher: String,
    @SerializedName("book_image")
    val image: String,
    val rank: Int,
    @SerializedName("buy_links")
    val buyLinks: List<BuyLinkDto>?
)
