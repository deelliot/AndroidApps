package com.delliott.whatscooking.domain

data class Recipe(
    val id: String,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val prepTime: Int,
    val cookTime: Int,
    val servings: Int,
    val cuisine: String,
    val image: String,
    val rating: Float = 0.0f,
    val isLocal: Boolean,
) {
    val totalTime: Int
        get() = prepTime + cookTime
}