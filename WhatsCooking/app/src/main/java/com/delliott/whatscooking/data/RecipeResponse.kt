package com.delliott.whatscooking.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllRecipesSearchResponse(
    val recipes: List<Recipe>,
)

@JsonClass(generateAdapter = true)
data class Recipe(
    val id: Int,
    val name: String,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val servings: Int,
    val cuisine: String,
    val image: String,
    val rating: Float
) {
    val totalTime : Int
        get() = prepTimeMinutes + cookTimeMinutes
}

@JsonClass(generateAdapter = true)
data class RecipeDetailResponse(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val servings: Int,
    val cuisine: String,
    val image: String,
    val rating: Float
){
    val totalTime : Int
        get() = prepTimeMinutes + cookTimeMinutes
}