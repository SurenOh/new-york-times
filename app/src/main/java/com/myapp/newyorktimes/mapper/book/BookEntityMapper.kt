package com.myapp.newyorktimes.mapper.book

import com.myapp.newyorktimes.db.entity.BookEntity
import com.myapp.newyorktimes.model.BookModel
import com.myapp.newyorktimes.util.mapper.EntityMapper

class BookEntityMapper : EntityMapper<BookEntity, BookModel> {
    override fun getModelFromEntity(entity: BookEntity) = BookModel (
        listName = entity.listName,
        name = entity.name,
        description = entity.description,
        author = entity.author,
        image = entity.image,
        publisher = entity.publisher,
        rank = entity.rank,
        buyLink = entity.buyLink
    )

    override fun getEntityFromModel(model: BookModel) = BookEntity (
        listName = model.listName,
        name = model.name,
        description = model.description,
        author = model.author,
        image = model.image,
        publisher = model.publisher,
        rank = model.rank,
        buyLink = model.buyLink
    )
}