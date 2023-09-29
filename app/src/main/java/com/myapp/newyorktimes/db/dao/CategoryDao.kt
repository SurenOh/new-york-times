package com.myapp.newyorktimes.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapp.newyorktimes.db.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: CategoryEntity)

    @Query("SELECT * FROM CategoryEntity")
    fun getCategories() : List<CategoryEntity>
}