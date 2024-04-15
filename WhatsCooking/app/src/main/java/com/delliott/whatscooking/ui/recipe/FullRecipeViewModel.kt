package com.delliott.whatscooking.ui.recipe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.delliott.whatscooking.dao.RecipeDatabase
import com.delliott.whatscooking.data.NetworkResult
import com.delliott.whatscooking.data.RecipeRepository
import com.delliott.whatscooking.domain.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FullRecipeViewModel(application: Application) : AndroidViewModel(application){
    private val recipeDatabase = RecipeDatabase.getDatabase(application)
    private val recipeRepository = RecipeRepository(recipeDatabase.dao())
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