package com.delliott.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delliott.recipes.data.Recipe
import com.delliott.recipes.data.RecipeRepository
import com.delliott.recipes.ui.RecipeViewModel
import com.delliott.recipes.ui.composables.RecipeCard
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
        Text(
            text = uiState.errorMessage!!
        )
        //show error
    } else {
        RecipeList(recipes = uiState.recipeList)
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    RecipesTheme {
        RecipeCard(
            Recipe(
                "chicken salad",
                10,
                10,
                4,
                "Meditteranean",
                "https://picsum.photos/id/237/200/300",
                4.5f
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListPreview() {
    val list = listOf(
        Recipe(
            "chicken salad",
            10,
            10,
            4,
            "Meditteranean",
            "https://picsum.photos/id/237/200/300",
            4.5f
        ),
        Recipe(
            "chicken salad",
            10,
            10,
            4,
            "Meditteranean",
            "https://picsum.photos/id/237/200/300",
            4.5f
        ),
        Recipe(
            "chicken salad",
            10,
            10,
            4,
            "Meditteranean",
            "https://picsum.photos/id/237/200/300",
            4.5f
        ),
        Recipe(
            "chicken salad",
            10,
            10,
            4,
            "Meditteranean",
            "https://picsum.photos/id/237/200/300",
            4.5f
        )
    )
    RecipesTheme {
        RecipeList(recipes = list)
    }
}