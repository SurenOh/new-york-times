package com.myapp.newyorktimes.network.dto.category

import com.google.gson.annotations.SerializedName

data class CategoryDto (
    @SerializedName("list_name")
    val name: String,
    @SerializedName("list_name_encoded")
    val encodedName: String,
    @SerializedName("newest_published_date")
    val publishedDate: String
)