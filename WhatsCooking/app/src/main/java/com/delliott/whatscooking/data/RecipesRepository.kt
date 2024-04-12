package com.delliott.whatscooking.data

import android.util.Log
import com.delliott.whatscooking.dao.RecipeDao
import com.delliott.whatscooking.dao.RecipeEntity
import com.delliott.whatscooking.domain.NewRecipeModel
import java.util.UUID

class RecipeRepository(private val recipeDao: RecipeDao) {

    suspend fun getAllRecipes(): NetworkResult<List<Recipe>> {
        return try {
            val result = ApiServiceProvider.client.fetchAllRecipes()
            NetworkResult.ApiSuccess(result.recipes)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }

    suspend fun getRecipeByMealType(mealType: String): NetworkResult<List<Recipe>> {
        return try {
            val result = ApiServiceProvider.client.fetchRecipesByMeal(mealType)
            NetworkResult.ApiSuccess(result.recipes)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }

    suspend fun getRecipeDetails(recipeId: Int): NetworkResult<RecipeDetailResponse> {
        Log.d("TEST", "LOADING")
        return try {
            val result = ApiServiceProvider.client.fetchRecipeDetails(recipeId)
            NetworkResult.ApiSuccess(result)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }

    suspend fun getSearchRecipes(searchTerm: String): NetworkResult<List<Recipe>> {
        return try {
            val result = ApiServiceProvider.client.fetchSearchRecipes(searchTerm)
            NetworkResult.ApiSuccess(result.recipes)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }

    suspend fun saveRecipe(newRecipe: NewRecipeModel) {
        val recipe = convertNewRecipe(newRecipe)
        recipeDao.upsertRecipe(recipe)
    }

    private fun convertNewRecipe(newRecipe: NewRecipeModel): RecipeEntity {

        val ingredientsString: String = newRecipe.ingredients.value.joinToString(prefix = "[", postfix = "]", separator = ", ")
        val instructionsString: String = newRecipe.instructions.value.joinToString(prefix = "[", postfix = "]", separator = ", ")

        return RecipeEntity(
            id = UUID.randomUUID().toString(),
            name = newRecipe.name.value,
            ingredients = ingredientsString,
            instructions = instructionsString,
            prepTime = newRecipe.prepTimeMinutes.value,
            cookTime = newRecipe.cookTimeMinutes.value,
            servings = newRecipe.servings.value,
            cuisine = newRecipe.cuisine.value,
            image = newRecipe.image,
            totalTime = newRecipe.totalTime,
        )
    }
}

sealed class NetworkResult<T : Any> {
    class ApiSuccess<T : Any>(val data: T) : NetworkResult<T>()
    class ApiError<T : Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class ApiException<T : Any>(val e: Throwable) : NetworkResult<T>()
}