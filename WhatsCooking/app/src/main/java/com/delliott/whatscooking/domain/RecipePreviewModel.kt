package com.delliott.whatscooking.domain

data class RecipePreviewModel(
    val id: String,
    val imageUrl: String,
    val name: String,
    val isLocal: Boolean
)