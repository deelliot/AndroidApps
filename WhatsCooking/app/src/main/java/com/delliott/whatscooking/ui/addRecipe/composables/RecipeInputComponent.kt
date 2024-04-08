package com.delliott.whatscooking.ui.addRecipe.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delliott.whatscooking.R

@Composable
fun RecipeInputComponent(
    onRecipeComponentAdded: (String) -> Unit = {},
    onRecipeComponentRemoved: (Int) -> Unit = {},
    label: String,
    itemList: List<String> = emptyList()
) {
    var text by remember {
        mutableStateOf(" ")
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RecipeInputField(
                text = text,
                label = label,
                modifier = Modifier.weight(1f),
                onValueChange = { text = it }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onRecipeComponentAdded(text)
            text = "" }) {
                Icon(Icons.Filled.Add, "Add component")
            }
        }
        Column {
//            for (item in itemList) {
//                InputItem(text = item, onClick = onRecipeComponentRemoved)
//            }
            itemList.forEachIndexed { index, item ->
                InputItem(text = item, onClick = { onRecipeComponentRemoved(index) } )

            }
        }
    }
}

@Composable
fun InputItem(text: String, onClick: () -> Unit = {}){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Filled.Close, modifier = Modifier.clickable { onClick() }, contentDescription = "add item")
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}
@Preview(showBackground = true)
@Composable
fun RecipeInputComponentPreview() {
    RecipeInputComponent(label = "Ingredients", itemList = listOf("garlic", "onion","chicken"))
}

@Preview(showBackground = true)
@Composable
fun InputItemPreview() {
    InputItem(text = "test")
}
