package com.myapp.newyorktimes.model

data class BookModel(
    val listName: String?,
    val name: String,
    val description: String,
    val author: String,
    val publisher: String,
    val image: String,
    val rank: Int,
    val buyLink: String?
)
