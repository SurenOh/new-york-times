package com.myapp.newyorktimes.util.mapper

interface EntityMapper<Entity, Model> {
    fun getModelFromEntity(entity: Entity): Model
    fun getEntityFromModel(model: Model): Entity

    fun getListModelsFromEntities(entities: List<Entity>) = ArrayList(entities.map { getModelFromEntity(it) })
}