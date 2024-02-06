package com.delliott.whatscooking.ui.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.delliott.whatscooking.R
import com.delliott.whatscooking.data.RecipeDetailResponse
import com.delliott.whatscooking.ui.recipe.composables.IngredientsCard
import com.delliott.whatscooking.ui.recipe.composables.InstructionsCard

@Composable
fun FullRecipeScreen(
    recipe: RecipeDetailResponse,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        item {
            AsyncImage(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .height(300.dp),
                model = recipe.image,
                contentDescription = null,
                error = painterResource(id = R.drawable.placeholder),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(top = 16.dp, bottom = 8.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "For ${recipe.servings} servings",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.inverseSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                IngredientsCard(ingredients = recipe.ingredients)
                Spacer(modifier = Modifier.height(4.dp))
                InstructionsCard(instructions = recipe.instructions)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FullRecipeScreenPreview() {
    val recipe: RecipeDetailResponse = RecipeDetailResponse(
        id = 1,
        name = "Classic Margherita Pizza",
        ingredients = listOf(
            "Pizza dough",
            "Tomato sauce",
            "Fresh mozzarella cheese",
            "Fresh basil leaves",
            "Olive oil",
            "Salt and pepper to taste"
        ),
        instructions = listOf(
            "Preheat the oven to 475°F (245°C).",
            "Roll out the pizza dough and spread tomato sauce evenly.",
            "Top with slices of fresh mozzarella and fresh basil leaves.",
            "Drizzle with olive oil and season with salt and pepper.",
            "Bake in the preheated oven for 12-15 minutes or until the crust is golden brown.",
            "Slice and serve hot."
        ),
        prepTimeMinutes = 20,
        cookTimeMinutes = 15,
        servings = 4,
        image = "https://cdn.dummyjson.com/recipe-images/1.webp",
        cuisine = "Italian",
        rating = 4.6f
    )
    FullRecipeScreen(recipe = recipe)
}

