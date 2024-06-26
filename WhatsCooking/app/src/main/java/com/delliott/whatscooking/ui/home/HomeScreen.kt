package com.delliott.whatscooking.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delliott.whatscooking.R
import com.delliott.whatscooking.domain.RecipePreviewModel
import com.delliott.whatscooking.ui.home.composables.AddRecipeFAB
import com.delliott.whatscooking.ui.home.composables.RecipeCarousel
import com.delliott.whatscooking.ui.home.composables.SearchTextField

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    recipesTopRated: List<RecipePreviewModel>,
    recipes30Mins: List<RecipePreviewModel>,
    searchTerm: String,
    onMealTypeSelected: (mealType: String) -> Unit = {},
    onRecipeSelected: (recipeId: String, isLocal: Boolean) -> Unit = { _,_ -> },
    onSearchChanged: (String) -> Unit = {},
    onSearchClicked: () -> Unit = {},
    onNewRecipeClicked: () -> Unit = {},
) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            SearchTextField(
                searchTerm,
                onValueChange = onSearchChanged,
                onSearchClicked = onSearchClicked
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small)),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                SelectMealTypeButton(mealType = "Dinner", onClick = {
                    onMealTypeSelected("dinner")

                })
                SelectMealTypeButton(mealType = "Lunch", onClick = {
                    onMealTypeSelected("lunch")
                })
                SelectMealTypeButton(mealType = "Breakfast", onClick = {
                    onMealTypeSelected("breakfast")
                })
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small)),
                horizontalArrangement = Arrangement.Center,
            ) {
                SelectMealTypeButton(mealType = "Snack", onClick = {
                    onMealTypeSelected("snack")
                })
                Spacer(modifier = Modifier.width(16.dp))
                SelectMealTypeButton(mealType = "Dessert", onClick = {
                    onMealTypeSelected("dessert")
                })
            }

            //add filterchips to buttons to allow selection of multiple

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "30 Minute Meals",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))

            RecipeCarousel(recipePreviewList = recipes30Mins, onRecipeSelected)

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Top Rated",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))

            RecipeCarousel(recipePreviewList = recipesTopRated, onRecipeSelected)
        }
        AddRecipeFAB(
            modifier = Modifier.align(Alignment.BottomEnd),
            context = LocalContext.current,
            onClick = { onNewRecipeClicked() })
    }
}

@Composable
fun SelectMealTypeButton(
    mealType: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(110.dp)
    ) {
        Text(
            text = mealType,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val string = "tomato"
    val list = listOf(
        RecipePreviewModel(id = "1", "www.123.com", "chicken sandwich", true),
        RecipePreviewModel(id = "1", "www.123.com", "chicken sandwich", true),
        RecipePreviewModel(id = "1", "www.123.com", "chicken sandwich", true),
        RecipePreviewModel(id = "1", "www.123.com", "chicken sandwich", true)
    )
    HomeScreen(recipes30Mins = list, recipesTopRated = list, searchTerm = string)
}
