package com.myapp.newyorktimes.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey
    val name: String,
    val listName: String? = "",
    val description: String,
    val author: String,
    val publisher: String,
    val image: String,
    val rank: Int,
    val buyLink: String?
)
