package com.myapp.newyorktimes.mapper.category

import com.myapp.newyorktimes.db.entity.CategoryEntity
import com.myapp.newyorktimes.model.CategoryModel
import com.myapp.newyorktimes.util.mapper.EntityMapper

class CategoryEntityMapper : EntityMapper<CategoryEntity, CategoryModel> {
    override fun getModelFromEntity(entity: CategoryEntity) = CategoryModel (
        name = entity.name,
        encodedName = entity.encodedName,
        publishedDate = entity.publishedDate
    )

    override fun getEntityFromModel(model: CategoryModel) = CategoryEntity (
        name = model.name,
        encodedName = model.encodedName,
        publishedDate = model.publishedDate
    )
}