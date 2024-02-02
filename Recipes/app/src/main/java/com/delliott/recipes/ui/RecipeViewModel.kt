package com.delliott.recipes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delliott.recipes.data.NetworkResult
import com.delliott.recipes.data.Recipe
import com.delliott.recipes.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val repository = RecipeRepository()
    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState: StateFlow<RecipeUiState> = _uiState

    init {
        viewModelScope.launch {
                val result = repository.getAllRecipes()
                when(result) {
                    is NetworkResult.ApiError -> TODO()
                    is NetworkResult.ApiException -> {
                        _uiState.value = RecipeUiState(errorMessage = result.e.message)
                    }
                    is NetworkResult.ApiSuccess -> {
                        _uiState.value = RecipeUiState(recipeList = result.data)
                    }
                }
            }
        }
    }

    data class RecipeUiState(
        val recipeList: List<Recipe> = emptyList(),
        val errorMessage: String? = null
    )

//        data class Success(val recipe: List<Recipe>): RecipeUiState()
//        data class Error(val message: String?): RecipeUiState()

        //to do: add a loading state
