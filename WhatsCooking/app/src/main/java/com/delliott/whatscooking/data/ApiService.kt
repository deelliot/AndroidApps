package com.delliott.whatscooking.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("recipes")
    suspend fun fetchAllRecipes(): AllRecipesSearchResponse
    @GET("recipes/meal-type/{mealType}")
    suspend fun fetchRecipesByMeal(
        //@Query(value = "meal-type") mealType: String
        @Path(value = "mealType") mealType: String
    ): AllRecipesSearchResponse

    @GET("recipes/{id}")
    suspend fun fetchRecipeDetails(
        @Path(value = "id") recipeId: Int
    ) :RecipeDetailResponse

    @GET("recipes/search")
    suspend fun fetchSearchRecipes(
        @Query(value = "q") searchTerm: String
    ): AllRecipesSearchResponse
}