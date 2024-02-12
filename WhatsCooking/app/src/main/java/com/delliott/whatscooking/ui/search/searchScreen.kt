package com.delliott.whatscooking.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.delliott.whatscooking.R
import com.delliott.whatscooking.data.Recipe

@Composable
fun SearchScreen(
    searchTerm: String,
    recipeList: List<Recipe>,
    onRecipeSelected: (recipeId: Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier){
        Text(
            text = "Showing results for: $searchTerm",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.inverseSurface,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn() {
            items(recipeList) {
                RecipeCard(recipe = it)
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {
    Card(
        modifier = Modifier
            .padding(all = 4.dp)
            .height(160.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row() {
            Box() {
                AsyncImage(
                    model = recipe.image,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    error = painterResource(id = R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(160.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 2.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = recipe.cuisine,
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painterResource(id = R.drawable.ic_restuarant),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${recipe.servings}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_schedule),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${recipe.totalTime} mins",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${recipe.rating}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeCardPreview() {
    val recipe: Recipe = (Recipe(
        id = 1,
        name = "chicken curry",
        prepTimeMinutes = 20,
        cookTimeMinutes = 30,
        servings = 4,
        cuisine = "Indian",
        image = "www.123.com",
        rating = 4.0f
    ))
    RecipeCard(recipe)
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    val list = listOf(
        Recipe(
            id = 1,
            name = "chicken curry",
            prepTimeMinutes = 20,
            cookTimeMinutes = 30,
            servings = 4,
            cuisine = "Indian",
            image = "www.123.com",
            rating = 4.0f
        ),
        Recipe(
            id = 1,
            name = "chicken curry",
            prepTimeMinutes = 20,
            cookTimeMinutes = 30,
            servings = 4,
            cuisine = "Indian",
            image = "www.123.com",
            rating = 4.0f
        ),
        Recipe(
            id = 1,
            name = "chicken curry",
            prepTimeMinutes = 20,
            cookTimeMinutes = 30,
            servings = 4,
            cuisine = "Indian",
            image = "www.123.com",
            rating = 4.0f
        )
    )
    SearchScreen("bananas", list)
}