package com.delliott.whatscooking.ui.addRecipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delliott.whatscooking.R
import com.delliott.whatscooking.domain.InputResult
import com.delliott.whatscooking.domain.NewRecipeModel
import com.delliott.whatscooking.ui.addRecipe.composables.RecipeInputComponent
import com.delliott.whatscooking.ui.addRecipe.composables.SimpleTextField

@Composable
fun AddRecipeScreen(
    newRecipe: NewRecipeModel,
    onNameChanged: (String) -> Unit = {},
    onCuisineSelected: (String) -> Unit = {},
    onServingSizeChanged: (String) -> Unit = {},
    onPrepTimeAdded: (String) -> Unit = {},
    onCookTimeAdded: (String) -> Unit = {},
    onIngredientAdded: (String) -> Unit = {},
    onIngredientRemoved: (Int) -> Unit = {},
    onInstructionAdded: (String) -> Unit = {},
    onInstructionRemoved: (Int) -> Unit = {}
) {
    Box(){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))

        ) {
            item {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    SimpleTextField(
                        text = newRecipe.name.value,
                        label = "Name",
                        onValueChange = onNameChanged,
                        errorMessage = newRecipe.name.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        SimpleTextField(
                            text = newRecipe.cuisine.value,
                            label = "Cuisine",
                            modifier = Modifier.weight(1f),
                            onValueChange = onCuisineSelected,
                            errorMessage = newRecipe.cuisine.error
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        SimpleTextField(
                            text = newRecipe.servings.value.toString(),
                            label = "Serving Size",
                            modifier = Modifier.weight(1f),
                            onValueChange = onServingSizeChanged,
                            errorMessage = newRecipe.servings.error,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        SimpleTextField(
                            text = newRecipe.prepTimeMinutes.value.toString(),
                            label = "Prep Time",
                            modifier = Modifier.weight(1f),
                            onValueChange = onPrepTimeAdded,
                            errorMessage = newRecipe.prepTimeMinutes.error,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        SimpleTextField(
                            text = newRecipe.cookTimeMinutes.value.toString(),
                            label = "Cooking Time",
                            modifier = Modifier.weight(1f),
                            onValueChange = onCookTimeAdded,
                            errorMessage = newRecipe.cookTimeMinutes.error,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    RecipeInputComponent(
                        label = "Ingredients",
                        itemList = newRecipe.ingredients.value,
                        onRecipeComponentAdded = onIngredientAdded,
                        onRecipeComponentRemoved = onIngredientRemoved
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    RecipeInputComponent(
                        label = "Instructions",
                        itemList = newRecipe.instructions.value,
                        onRecipeComponentAdded = onInstructionAdded,
                        onRecipeComponentRemoved = onInstructionRemoved
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = MaterialTheme.colorScheme.secondary),
            onClick = { /*TODO*/ }) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun AddRecipeScreenPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        AddRecipeScreen(
            newRecipe = NewRecipeModel(
                id = "123",
                name = InputResult("pizza"),
                ingredients = InputResult(listOf("onion", "dough", "pineapple")),
                instructions = InputResult(listOf("turn on oven", "bake", "enjoy")),
                prepTimeMinutes = InputResult(0),
                cookTimeMinutes = InputResult(0),
                cuisine = InputResult("italian"),
                servings = InputResult(0),
                image = "blah"
            )
        )
    }
}