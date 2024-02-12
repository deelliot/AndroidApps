package com.delliott.whatscooking.data

import android.util.Log

class RecipeRepository {
    suspend fun getAllRecipes(): NetworkResult<List<Recipe>> {
        return try {
            val result = ApiServiceProvider.client.fetchAllRecipes()
            NetworkResult.ApiSuccess(result.recipes)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }

    suspend fun getRecipeByMealType(mealType: String) : NetworkResult<List<Recipe>> {
        return try {
            val result = ApiServiceProvider.client.fetchRecipesByMeal(mealType)
            NetworkResult.ApiSuccess(result.recipes)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }

    suspend fun getRecipeDetails(recipeId: Int) : NetworkResult<RecipeDetailResponse> {
        Log.d("TEST", "LOADING")
        return try {
            val result = ApiServiceProvider.client.fetchRecipeDetails(recipeId)
            NetworkResult.ApiSuccess(result)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }

    suspend fun getSearchRecipes(searchTerm: String) : NetworkResult<List<Recipe>> {
        return try {
            val result = ApiServiceProvider.client.fetchSearchRecipes(searchTerm)
            NetworkResult.ApiSuccess(result.recipes)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }
}

sealed class NetworkResult<T : Any> {
    class ApiSuccess<T : Any>(val data: T) : NetworkResult<T>()
    class ApiError<T : Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class ApiException<T : Any>(val e: Throwable) : NetworkResult<T>()
}