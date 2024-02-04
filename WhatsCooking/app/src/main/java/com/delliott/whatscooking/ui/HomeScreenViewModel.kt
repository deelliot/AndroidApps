package com.delliott.whatscooking.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delliott.whatscooking.data.NetworkResult
import com.delliott.whatscooking.data.RecipeRepository
import com.delliott.whatscooking.domain.RecipePreviewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val recipeRepository = RecipeRepository()
    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState
        get() = _uiState

    fun fetchRecipesByMealType(mealType: String) {
        if (mealType.isNotBlank()) {
            viewModelScope.launch {
                val result = recipeRepository.getRecipebyMealType(mealType)
                when (result) {
                    is NetworkResult.ApiError -> TODO()
                    is NetworkResult.ApiException -> {
                        _uiState.value = RecipeUiState(errorMessage = result.e.message)
                    }

                    is NetworkResult.ApiSuccess -> {
                        val recipes30Mins =
                            result.data.filter { it.totalTime <= 30 }.map { recipe ->
                                RecipePreviewModel(
                                    imageUrl = recipe.image,
                                    name = recipe.name
                                )
                            }
                        val recipesTopRated =
                            result.data.filter { it.rating >= 4.0 }.map { recipe ->
                                RecipePreviewModel(
                                    imageUrl = recipe.image,
                                    name = recipe.name
                                )
                            }
                        _uiState.value = RecipeUiState(
                            recipes30mins = recipes30Mins,
                            recipesTopRated = recipesTopRated
                        )
                    }
                }
            }
        }
    }
}

data class RecipeUiState(
    val recipes30mins: List<RecipePreviewModel> = emptyList(),
    val recipesTopRated: List<RecipePreviewModel> = emptyList(),
    val errorMessage: String? = null,
)