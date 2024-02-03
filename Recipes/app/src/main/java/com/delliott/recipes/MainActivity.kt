package com.delliott.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delliott.recipes.data.RecipeRepository
import com.delliott.recipes.ui.RecipeViewModel
import com.delliott.recipes.ui.composables.HandleErrors
import com.delliott.recipes.ui.composables.RecipeList
import com.delliott.recipes.ui.theme.RecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = RecipeRepository()
        val vm: RecipeViewModel by viewModels()

        setContent {
            RecipesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeScreen(vm)
                }
            }
        }
    }
}

@Composable
fun RecipeScreen(recipeViewModel: RecipeViewModel = viewModel()) {
    val uiState by recipeViewModel.uiState.collectAsState()
    if (uiState.errorMessage != null) {
        HandleErrors(errorMessage = uiState.errorMessage!!)
    } else {
        RecipeList(recipes = uiState.recipeList)
    }
}


