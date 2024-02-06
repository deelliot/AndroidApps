package com.delliott.whatscooking.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delliott.whatscooking.data.NetworkResult
import com.delliott.whatscooking.data.Recipe
import com.delliott.whatscooking.data.RecipeRepository
import com.delliott.whatscooking.domain.RecipePreviewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val recipeRepository = RecipeRepository()
    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState
        get() = _uiState


    private fun List<Recipe>.filterRecipes(filterOption: (Recipe) -> Boolean  ) : List<RecipePreviewModel> {
       return this.filter(filterOption).map {recipe ->
            RecipePreviewModel(
                id = recipe.id,
                imageUrl = recipe.image,
                name = recipe.name
            )
        }
    }

    fun fetchAllRecipes() {
        viewModelScope.launch {
            val result = recipeRepository.getAllRecipes()
            when (result) {
                is NetworkResult.ApiError -> TODO()
                is NetworkResult.ApiException -> {
                    _uiState.value = RecipeUiState(errorMessage = result.e.message)
                }
                is NetworkResult.ApiSuccess -> {
                    val recipes30Mins = result.data.filterRecipes({ it.totalTime <= 30 })
                    val recipesTopRated = result.data.filterRecipes({ it.rating >= 4.0 })
                    _uiState.value = RecipeUiState(
                        recipes30mins = recipes30Mins,
                        recipesTopRated = recipesTopRated
                    )
                }
            }
        }
    }

    fun fetchRecipesByMealType(mealType: String) {
        if (mealType.isNotBlank()) {
            viewModelScope.launch {
                val result = recipeRepository.getRecipeByMealType(mealType)
                when (result) {
                    is NetworkResult.ApiError -> TODO()
                    is NetworkResult.ApiException -> {
                        _uiState.value = RecipeUiState(errorMessage = result.e.message)
                    }

                    is NetworkResult.ApiSuccess -> {
                        //clearList()
                        val recipes30Mins = result.data.filterRecipes({ it.totalTime <= 30 })
                        val recipesTopRated = result.data.filterRecipes({ it.rating >= 4.0 })
                        _uiState.value = RecipeUiState(
                            recipes30mins = recipes30Mins,
                            recipesTopRated = recipesTopRated
                        )
                    }
                }
            }
        }
    }

//    private fun clearList() {
//        val emptyList: List<RecipePreviewModel> = emptyList()
//        _uiState.value = RecipeUiState(
//            recipes30mins = emptyList,
//            recipesTopRated = emptyList
//        )
//    }
}

data class RecipeUiState(
    val recipes30mins: List<RecipePreviewModel> = emptyList(),
    val recipesTopRated: List<RecipePreviewModel> = emptyList(),
    val errorMessage: String? = null,
)
