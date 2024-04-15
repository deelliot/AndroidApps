package com.delliott.whatscooking.data

import com.delliott.whatscooking.dao.RecipeDao
import com.delliott.whatscooking.dao.RecipeEntity
import com.delliott.whatscooking.domain.NewRecipeModel
import com.delliott.whatscooking.domain.Recipe
import java.util.UUID

class RecipeRepository(private val recipeDao: RecipeDao) {

    suspend fun getAllRecipes(): NetworkResult<List<Recipe>> {
        return try {
            val result = ApiServiceProvider.client.fetchAllRecipes()
            val networkList = result.recipes.map {
                convertRecipeResponseToDomainModel(it)
            }
            val localList = recipeDao.getRecipeOrderedByName().map {
                convertRoomEntityToDomainModel(it)
            }
            NetworkResult.ApiSuccess(networkList + localList)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }


    suspend fun getRecipeByMealType(mealType: String): NetworkResult<List<Recipe>> {
        return try {
            val result = ApiServiceProvider.client.fetchRecipesByMeal(mealType)
            val networkList = result.recipes.map {
                convertRecipeResponseToDomainModel(it)
            }
            NetworkResult.ApiSuccess(networkList)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }

    //    suspend fun getRecipeDetails(recipeId: String): NetworkResult<Recipe> {
//        return try {
//            val result = ApiServiceProvider.client.fetchRecipeDetails(recipeId.toInt())
//            val recipe = convertRecipeResponseToDomainModel(result)
//            NetworkResult.ApiSuccess(recipe)
//        } catch (e: Exception) {
//            NetworkResult.ApiException(e)
//        }
//    }
    suspend fun getRecipeDetails(recipeId: String, isLocal: Boolean): NetworkResult<Recipe> {
        val recipe: Recipe
        return try {
            if (isLocal) {
                recipe = (convertRoomEntityToDomainModel(recipeDao.getRecipeById(recipeId)))
            } else {
                val result = ApiServiceProvider.client.fetchRecipeDetails(recipeId.toInt())
                recipe = convertRecipeResponseToDomainModel(result)
            }
            NetworkResult.ApiSuccess(recipe)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }

    suspend fun getSearchRecipes(searchTerm: String): NetworkResult<List<Recipe>> {
        return try {
            val result = ApiServiceProvider.client.fetchSearchRecipes(searchTerm)
            val networkList = result.recipes.map {
                convertRecipeResponseToDomainModel(it)
            }
            NetworkResult.ApiSuccess(networkList)
        } catch (e: Exception) {
            NetworkResult.ApiException(e)
        }
    }

    suspend fun saveRecipe(newRecipe: NewRecipeModel) {
        val recipe = convertNewRecipeToRoomEntity(newRecipe)
        recipeDao.upsertRecipe(recipe)
    }

    private fun convertNewRecipeToRoomEntity(newRecipe: NewRecipeModel): RecipeEntity {

        val ingredientsString: String = newRecipe.ingredients.value.joinToString(
            prefix = "[",
            postfix = "]",
            separator = ", "
        )
        val instructionsString: String = newRecipe.instructions.value.joinToString(
            prefix = "[",
            postfix = "]",
            separator = ", "
        )

        return RecipeEntity(
            id = UUID.randomUUID().toString(),
            name = newRecipe.name.value,
            ingredients = ingredientsString,
            instructions = instructionsString,
            prepTime = newRecipe.prepTimeMinutes.value,
            cookTime = newRecipe.cookTimeMinutes.value,
            servings = newRecipe.servings.value,
            cuisine = newRecipe.cuisine.value,
            image = newRecipe.image
        )
    }
}

private fun convertRoomEntityToDomainModel(old: RecipeEntity): Recipe {
    return Recipe(
        id = old.id,
        name = old.name,
        ingredients = old.ingredients.split(", "),
        instructions = old.instructions.split(", "),
        prepTime = old.prepTime,
        cookTime = old.cookTime,
        servings = old.servings,
        cuisine = old.cuisine,
        image = old.image,
        rating = 0.0f,
        isLocal = true
    )
}

private fun convertRecipeResponseToDomainModel(old: RecipeDetailResponse): Recipe {
    return Recipe(
        id = old.id.toString(),
        name = old.name,
        ingredients = old.ingredients,
        instructions = old.instructions,
        prepTime = old.prepTimeMinutes,
        cookTime = old.cookTimeMinutes,
        servings = old.servings,
        cuisine = old.cuisine,
        image = old.image,
        rating = old.rating,
        isLocal = false
    )
}

sealed class NetworkResult<T : Any> {
    class ApiSuccess<T : Any>(val data: T) : NetworkResult<T>()
    class ApiError<T : Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class ApiException<T : Any>(val e: Throwable) : NetworkResult<T>()
}