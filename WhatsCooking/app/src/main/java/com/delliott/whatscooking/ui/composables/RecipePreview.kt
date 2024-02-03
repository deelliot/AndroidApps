package com.delliott.whatscooking.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun RecipePreview(recipePreview: RecipePreviewModel, onClick:() -> Unit = {}) {

    Card(
        modifier = Modifier.padding(all = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
        // TODO: add on click
    ) {
        Box {
            AsyncImage(
                model = recipePreview.imageUrl,
                contentDescription = null,
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(140.dp)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                text = recipePreview.name
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipePreviewPreview() {
    RecipePreview(RecipePreviewModel("www.123.com", "chicken sandwich"))
}