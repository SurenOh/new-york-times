package com.myapp.newyorktimes.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity (
    @PrimaryKey
    val name: String,
    val encodedName: String,
    val publishedDate: String
)