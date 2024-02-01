package com.delliott.recipes.data

class RecipeRepository {
    suspend fun getAllRecipes(): NetworkResult<List<Recipe>> {
        try {
            val result = ApiServiceProvider.client.fetchAllRecipes()
            return NetworkResult.ApiSuccess(result.recipes)
        } catch (e: Exception) {
            return NetworkResult.ApiException(e)
        }
    }
}

sealed class NetworkResult<T : Any> {
    class ApiSuccess<T : Any>(val data: T) : NetworkResult<T>()
    class ApiError<T : Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class ApiException<T : Any>(val e: Throwable) : NetworkResult<T>()
}