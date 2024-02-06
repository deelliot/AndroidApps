package com.delliott.whatscooking.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.delliott.whatscooking.domain.RecipePreviewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipePreview(recipePreview: RecipePreviewModel, onRecipeSelected: (recipeId: Int) -> Unit = {}) {

    Card(
        modifier = Modifier.padding(all = 4.dp).size(140.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            onRecipeSelected(recipePreview.id)
        }
    ) {
        Box {
            AsyncImage(
                model = recipePreview.imageUrl,
                contentDescription = null,
                error = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(140.dp)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(4.dp)
                    .fillMaxWidth(),
                text = recipePreview.name,
                style = MaterialTheme.typography.bodyMedium,
                minLines = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipePreviewPreview() {
    RecipePreview(RecipePreviewModel(id = 1,"www.123.com", "chicken sandwich"))
}