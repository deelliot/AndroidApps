package com.delliott.whatscooking.ui.addRecipe.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun IngredientsCard(
    onRecipeComponentAdded: (String, String, String) -> Unit = {_,_,_ ->},
    onRecipeComponentRemoved: (Int) -> Unit = {},
    label: String,
    itemList: List<String> = emptyList()
) {
    var text by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("")}
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
            SimpleTextField(
                modifier = Modifier.weight(0.5f),
                text = quantity,
                label = "qty",
                onValueChange = { quantity = it }
            )
            Spacer(modifier = Modifier.width(8.dp))
            UnitExposedDropDownMenu(
                modifier = Modifier.weight(0.75f),
                options = listOf("g", "ml", " cup", "tbsp", "tsp", " can", " pkt"),
                unit = unit,
                onValueChange = { unit = it }
            )
            Spacer(modifier = Modifier.width(8.dp))
            RecipeInputField(
                text = text,
                label = label,
                modifier = Modifier.weight(1f),
                onValueChange = { text = it }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onRecipeComponentAdded(quantity, unit, text)
                text = "" }) {
                Icon(Icons.Filled.Add, "Add component")
            }
        }
        Column {
            itemList.forEachIndexed { index, item ->
                InputItem(text = item, onClick = { onRecipeComponentRemoved(index) } )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitExposedDropDownMenu(
    modifier: Modifier = Modifier,
    options: List<String>,
    unit: String,
    onValueChange: (String) -> Unit, // on unit changed
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = { newValue ->
            isExpanded = newValue
        }
    ) {
        TextField(
            value = unit,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            placeholder = { Text(modifier = Modifier, text = "unit") },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            for (item in options) {
                DropdownMenuItem(
                    text = {
                        Text(item)
                    },
                    onClick = {
                        onValueChange(item)
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientsCardPreview() {
    IngredientsCard(label = "Ingredients", itemList = listOf("garlic", "onion","chicken"))
}