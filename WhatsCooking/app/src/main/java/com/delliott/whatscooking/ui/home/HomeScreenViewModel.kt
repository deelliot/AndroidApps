package com.delliott.whatscooking.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.delliott.whatscooking.dao.RecipeDatabase
import com.delliott.whatscooking.data.NetworkResult
import com.delliott.whatscooking.data.Recipe
import com.delliott.whatscooking.data.RecipeRepository
import com.delliott.whatscooking.domain.RecipePreviewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val recipeDatabase = RecipeDatabase.getDatabase(application)
    private val recipeRepository = RecipeRepository(recipeDatabase.dao())
    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState
        get() = _uiState

    private fun List<Recipe>.filterRecipes(filterOption: (Recipe) -> Boolean): List<RecipePreviewModel> {
        return this.filter(filterOption).map { recipe ->
            RecipePreviewModel(
                id = recipe.id,
                imageUrl = recipe.image,
                name = recipe.name
            )
        }
    }

    fun fetchAllRecipes() {
        viewModelScope.launch {
            when (val result = recipeRepository.getAllRecipes()) {
                is NetworkResult.ApiError -> TODO()
                is NetworkResult.ApiException -> {
                    _uiState.value = RecipeUiState(errorMessage = result.e.message)
                }
                is NetworkResult.ApiSuccess -> {
                    val recipes30Mins = result.data.filterRecipes {it.totalTime <= 30 }

                    // function literal: function isn't declared, passed immediately as expression.
                    // fun compare(totalTime: Int, filterOption: 30): Boolean = totalTime < filterOption

                    val recipesTopRated = result.data.filterRecipes { it.rating >= 4.0 }
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
                when (val result = recipeRepository.getRecipeByMealType(mealType)) {
                    is NetworkResult.ApiError -> TODO()
                    is NetworkResult.ApiException -> {
                        _uiState.value = RecipeUiState(errorMessage = result.e.message)
                    }
                    is NetworkResult.ApiSuccess -> {
                        //clearList()
                        val recipes30Mins = result.data.filterRecipes { it.totalTime <= 30 }
                        val recipesTopRated = result.data.filterRecipes { it.rating >= 4.0 }
                        _uiState.value = RecipeUiState(
                            recipes30mins = recipes30Mins,
                            recipesTopRated = recipesTopRated,
                        )
                    }
                }
            }
        }
    }

    fun setSearchTerm(searchTerm: String) {
        _uiState.value = _uiState.value.copy(searchTerm = searchTerm)
    }
}

data class RecipeUiState(
    val recipes30mins: List<RecipePreviewModel> = emptyList(),
    val recipesTopRated: List<RecipePreviewModel> = emptyList(),
    val allRecipes: List<RecipePreviewModel> = emptyList(),
    val searchTerm: String = "",
    val errorMessage: String? = null,
)
