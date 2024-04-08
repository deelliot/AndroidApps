package com.delliott.whatscooking.domain

data class NewRecipeModel (
    val id: String,
    val name: InputResult<String>,
    val ingredients: InputResult<List<String>>,
    val instructions: InputResult<List<String>>,
    val prepTimeMinutes: InputResult<Int>,
    val cookTimeMinutes: InputResult<Int>,
    val servings: InputResult<Int>,
    val cuisine: InputResult<String>,
    val image: String,
){
    val totalTime : Int
        get() = prepTimeMinutes.value + cookTimeMinutes.value
}

data class InputResult<T>(
    val value: T,
    val error: String? = null,
)