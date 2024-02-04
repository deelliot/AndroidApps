package com.delliott.whatscooking.ui.composables

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delliott.whatscooking.R
import com.delliott.whatscooking.domain.RecipePreviewModel

@Composable

fun HomeScreen(

    recipesTopRated: List<RecipePreviewModel>,
    recipes30Mins: List<RecipePreviewModel>,
    onMealTypeSelected: (mealType: String) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        SearchTextField()
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            SelectMealTypeButton(mealType = "Dinner", onClick = {
                onMealTypeSelected("Dinner")

            })
            SelectMealTypeButton(mealType = "Lunch", onClick = {
                onMealTypeSelected("Lunch")
            })
            SelectMealTypeButton(mealType = "Breakfast", onClick = {
                onMealTypeSelected("Breakfast")
            })
        }

        //Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.Center,
        ) {
            SelectMealTypeButton(mealType = "Snack", onClick = {
                onMealTypeSelected("Snack")
            })
            Spacer(modifier = Modifier.width(16.dp))
            SelectMealTypeButton(mealType = "Dessert", onClick = {
                onMealTypeSelected("Dessert") })
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "30 minute meals",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(4.dp))

        RecipeCarousel(recipePreviewList = recipes30Mins)

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Top Rated",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(4.dp))

        RecipeCarousel(recipePreviewList = recipesTopRated)
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
    val list = listOf(
        RecipePreviewModel("www.123.com", "chicken sandwich"),
        RecipePreviewModel("www.123.com", "chicken sandwich"),
        RecipePreviewModel("www.123.com", "chicken sandwich"),
        RecipePreviewModel("www.123.com", "chicken sandwich"))
    HomeScreen(list, list)
}
