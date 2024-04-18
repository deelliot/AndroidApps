package com.delliott.whatscooking.ui.search

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
class SearchViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState
        get() = _uiState

    fun fetchSearchRecipes(searchTerm: String) {
        viewModelScope.launch {
            val result = recipeRepository.getSearchRecipes(searchTerm)
            when (result) {
                is NetworkResult.ApiError -> TODO()
                is NetworkResult.ApiException -> {
                    _uiState.value = SearchUiState(errorMessage = result.e.message)
                }
                is NetworkResult.ApiSuccess -> {
                    _uiState.value = SearchUiState(recipes = result.data)
                }
            }
        }
    }
}


data class SearchUiState(
    val recipes: List<Recipe> = emptyList(),
    val errorMessage: String? = null
)