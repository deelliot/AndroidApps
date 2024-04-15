package com.delliott.whatscooking.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val ingredients: String,
    val instructions: String,
    val prepTime: Int,
    val cookTime: Int,
    val servings: Int,
    val cuisine: String,
    val image: String,
) {
    val totalTime : Int
        get() = prepTime + cookTime
}
