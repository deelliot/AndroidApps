package com.delliott.recipes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllRecipesSearchResponse(
    val recipes: List<Recipe>,
)

@JsonClass(generateAdapter = true)
data class Recipe(
    val name: String,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val servings: Int,
    val cuisine: String,
    val image: String,
    val rating: Float,
)