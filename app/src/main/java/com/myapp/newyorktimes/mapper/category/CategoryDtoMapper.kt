package com.myapp.newyorktimes.mapper.category

import com.myapp.newyorktimes.model.CategoryModel
import com.myapp.newyorktimes.network.dto.category.CategoryDto
import com.myapp.newyorktimes.util.mapper.DtoMapper

class CategoryDtoMapper : DtoMapper<CategoryDto, CategoryModel> {
    override fun getModelFromDto(listName: String?, dto: CategoryDto) = CategoryModel (
        name = dto.name,
        encodedName = dto.encodedName,
        publishedDate = dto.publishedDate
    )
}