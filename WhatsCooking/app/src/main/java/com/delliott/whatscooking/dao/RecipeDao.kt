package com.delliott.whatscooking.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RecipeDao {

    @Upsert
    suspend fun upsertRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipeEntity ORDER BY name ASC")
    suspend fun getRecipeOrderedByName():List<RecipeEntity>

    @Query("SELECT * FROM recipeEntity WHERE id = :id")
    suspend fun getRecipeById(id: String): RecipeEntity
}