package com.delliott.whatscooking.ui.home.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delliott.whatscooking.domain.RecipePreviewModel

@Composable
fun RecipeCarousel(recipePreviewList: List<RecipePreviewModel>, onRecipeSelected: (recipeId: String, isLocal: Boolean) -> Unit = { _,_ -> }) {
    LazyRow(modifier = Modifier.padding(4.dp)) {
        items(recipePreviewList) {
            RecipePreview(it, onRecipeSelected)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCarouselPreview() {
    val list = listOf(
        RecipePreviewModel(id = "1","www.123.com", "chicken sandwich", true),
        RecipePreviewModel(id = "1","www.123.com", "chicken sandwich", true),
        RecipePreviewModel(id = "1","www.123.com", "chicken sandwich", true),
        RecipePreviewModel(id = "1","www.123.com", "chicken sandwich", true),
    )
    RecipeCarousel(list)
}