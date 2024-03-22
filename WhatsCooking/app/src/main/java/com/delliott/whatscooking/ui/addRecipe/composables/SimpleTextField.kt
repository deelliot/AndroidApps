package com.delliott.whatscooking.ui.addRecipe.composables

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun SimpleTextField(label: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("Hello") }
    TextField(
        modifier =  modifier,
        value = text,
        onValueChange = { text = it },
        label = { Text(label) }
    )
}