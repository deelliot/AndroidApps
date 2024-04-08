package com.delliott.whatscooking.ui.addRecipe

import android.util.Log
import androidx.lifecycle.ViewModel
import com.delliott.whatscooking.domain.InputResult
import com.delliott.whatscooking.domain.NewRecipeModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

class AddRecipeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NewRecipeState())
    val uiState //type stateFlow (make immutable)
        get() = _uiState


    fun addName(name: String) {
        val newRecipe = uiState.value.newRecipe.copy(name = InputResult(name))
        _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
    }

    fun addCuisine(cuisine: String) {
        val newRecipe = uiState.value.newRecipe.copy(cuisine = InputResult(cuisine))
        _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
    }

    private fun stringToInt(value: String): Int {
        return if (value.isBlank()) 0
        else if (value.toIntOrNull() == null) -1
        else (value.toInt())
    }

    fun addServingSize(servingSize: String) {
        val convertedServingSize = stringToInt(servingSize)
        if (convertedServingSize == -1) {
            val newRecipe = uiState.value.newRecipe.copy(
                servings = InputResult(0, error = "enter value in minutes"))
            _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
        } else {
            val newRecipe = uiState.value.newRecipe.copy(
                servings = InputResult(convertedServingSize))
            _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
        }
    }


    fun addPrepTime(prepTime: String) {
        val convertedPrepTime = stringToInt(prepTime)
        if (convertedPrepTime == -1) {
            val newRecipe = uiState.value.newRecipe.copy(
                prepTimeMinutes = InputResult(0, error = "enter value in minutes"))
            _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
        } else {
            val newRecipe = uiState.value.newRecipe.copy(
                prepTimeMinutes = InputResult(convertedPrepTime))
            _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
        }
    }

    fun addCookTime(cookTime: String) {
        val convertedCookTime = stringToInt(cookTime)
        if (convertedCookTime == -1) {
            val newRecipe = uiState.value.newRecipe.copy(
                cookTimeMinutes = InputResult(0, error = "enter value in minutes"))
            _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
        } else {
            val newRecipe = uiState.value.newRecipe.copy(
                cookTimeMinutes = InputResult(convertedCookTime))
            _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
        }
    }

    fun addIngredient(ingredient: String) {
        val currentList = _uiState.value.newRecipe.ingredients.value.toMutableList()
        currentList.add(ingredient)
        val newRecipe = uiState.value.newRecipe.copy(
            ingredients = InputResult(currentList))
        _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
    }

    fun removeIngredient(ingredient: Int) {
        val currentList = _uiState.value.newRecipe.ingredients.value.toMutableList()
        currentList.removeAt(ingredient)
        val newRecipe = uiState.value.newRecipe.copy(
            ingredients = InputResult(currentList))
        _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
    }

    fun addInstruction(instruction: String) {
        val currentList = _uiState.value.newRecipe.instructions.value.toMutableList()
        currentList.add(instruction)
        val newRecipe = uiState.value.newRecipe.copy(
            instructions = InputResult(currentList))
        _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
    }

    fun removeInstruction(instruction: Int) {
        val currentList = _uiState.value.newRecipe.instructions.value.toMutableList()
        currentList.removeAt(instruction)
        val newRecipe = uiState.value.newRecipe.copy(
            instructions = InputResult(currentList))
        _uiState.value = _uiState.value.copy(newRecipe = newRecipe)
    }

    fun saveRecipe() {
        Log.i("NewRecipe", "New Recipe: ${_uiState.value.newRecipe}")
    }
}


data class NewRecipeState(
    val newRecipe: NewRecipeModel = NewRecipeModel(
        id = UUID.randomUUID().toString(),
        name = InputResult(""),
        ingredients = InputResult(emptyList()),
        instructions = InputResult(emptyList()),
        prepTimeMinutes = InputResult(0),
        cookTimeMinutes = InputResult(0),
        servings = InputResult(0),
        cuisine = InputResult(""),
        image = ""
    ),
//    val ingredientsList: List<String> = emptyList(),
//    val instructionsList: List<String> = emptyList(),
    val errorMessage: String? = null,
)