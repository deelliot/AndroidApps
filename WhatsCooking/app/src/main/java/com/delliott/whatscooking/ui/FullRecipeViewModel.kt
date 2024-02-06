package com.delliott.whatscooking.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delliott.whatscooking.data.NetworkResult
import com.delliott.whatscooking.data.RecipeDetailResponse
import com.delliott.whatscooking.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FullRecipeViewModel: ViewModel() {
    private val recipeRepository = RecipeRepository()
    private val _uiState = MutableStateFlow(RecipeDetailsUiState())
    val uiState
        get() = _uiState

    fun fetchRecipeDetails(recipeId: Int) {
        viewModelScope.launch {
            val result = recipeRepository.getRecipeDetails(recipeId)
            when (result) {
                is NetworkResult.ApiError -> TODO()
                is NetworkResult.ApiException -> {
                    _uiState.value = RecipeDetailsUiState(errorMessage = result.e.message)
                }
                is NetworkResult.ApiSuccess -> {
                    _uiState.value = RecipeDetailsUiState(recipeDetails = result.data)
                }
            }
        }
    }

}

data class RecipeDetailsUiState(
    val recipeDetails: RecipeDetailResponse? = null,
    val errorMessage: String? = null,
)