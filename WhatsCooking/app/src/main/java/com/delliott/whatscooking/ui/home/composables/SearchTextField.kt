package com.delliott.whatscooking.ui.home.composables

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchTextField(searchTerm: String, onValueChange: (String) -> Unit,
                    onSearchClicked: () -> Unit) {

    OutlinedTextField(
        value = searchTerm,
        onValueChange = {
            onValueChange(it) },
        label = { Text("Search") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null )},
        maxLines = 1,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.secondary),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearchClicked() })
    )
}