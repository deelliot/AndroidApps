package com.delliott.whatscooking.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delliott.whatscooking.data.NetworkResult
import com.delliott.whatscooking.data.RecipeRepository
import com.delliott.whatscooking.domain.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FullRecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeDetailsUiState())
    val uiState
        get() = _uiState

    fun fetchRecipeDetails(recipeId: String, isLocal: Boolean) {
        viewModelScope.launch {
            val result = recipeRepository.getRecipeDetails(recipeId, isLocal)
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
    val recipeDetails: Recipe? = null,
    val errorMessage: String? = null,
)