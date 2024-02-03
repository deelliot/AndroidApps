package com.delliott.recipes.ui.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.delliott.recipes.data.Recipe

@Composable
fun RecipeList(recipes: List<Recipe>) {
    LazyColumn {
        items(recipes) { recipe ->
            RecipeCard(recipe)
        }
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
    RecipeList(recipes = list)
}