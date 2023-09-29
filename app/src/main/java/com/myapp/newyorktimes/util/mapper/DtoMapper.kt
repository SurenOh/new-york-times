package com.myapp.newyorktimes.util.mapper

interface DtoMapper<Dto, Model> {
    fun getModelFromDto(listName: String? = null, dto: Dto): Model

    fun getListModelsFromDto(list: List<Dto>, listName: String? = null) = ArrayList(list.map { getModelFromDto(listName, it) })
}