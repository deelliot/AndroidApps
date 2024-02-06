package com.delliott.whatscooking.ui.recipe.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun IngredientsCard(ingredients: List<String>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        ingredients.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = it,
                    modifier = Modifier
                        .weight(1f, true)
                )

                Text(
                    text = Random.nextInt(0, 100).toString(),
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientsCardPreview() {
    val list: List<String> = listOf(
        "Pizza dough",
        "Tomato sauce",
        "Fresh mozzarella cheese",
        "Fresh basil leaves",
        "Olive oil",
        "Salt and pepper to taste"
    )
    IngredientsCard(ingredients = list)
}
