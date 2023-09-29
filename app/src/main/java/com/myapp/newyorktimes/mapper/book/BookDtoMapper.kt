package com.myapp.newyorktimes.mapper.book

import com.myapp.newyorktimes.model.BookModel
import com.myapp.newyorktimes.network.dto.book.BookDto
import com.myapp.newyorktimes.util.mapper.DtoMapper

class BookDtoMapper : DtoMapper<BookDto, BookModel> {
    override fun getModelFromDto(listName: String?, dto: BookDto) = BookModel (
        listName = listName,
        name = dto.name,
        description = dto.description,
        author = dto.author,
        publisher = dto.publisher,
        image = dto.image,
        rank = dto.rank,
        buyLink = dto.buyLinks?.get(0)?.url
    )
}