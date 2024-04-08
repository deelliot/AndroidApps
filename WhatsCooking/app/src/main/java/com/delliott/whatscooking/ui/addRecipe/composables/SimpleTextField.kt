package com.delliott.whatscooking.ui.addRecipe.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleTextField(
    text: String,
    label: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
) {
    Column(
        modifier = modifier
    ) {
        TextField(
            value = text,
//            modifier = modifier,
            onValueChange = { onValueChange(it) },
            label = { Text(label) },
            keyboardOptions = keyboardOptions,
        )
        Box(
            modifier = Modifier
                .requiredHeight(16.dp)
                .padding(start = 16.dp, end = 12.dp)
        ) {
            CompositionLocalProvider(
                LocalTextStyle provides LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    color = if (errorMessage != null) MaterialTheme.colorScheme.error else LocalTextStyle.current.color
                )
            ) {
                if (errorMessage != null) {
                    Text(
                        text = errorMessage
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeInputField(
    text: String,
    label: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = text,
        modifier = modifier,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        textStyle = TextStyle(color = MaterialTheme.colorScheme.secondary),
    )
}

@Preview(showBackground = true)
@Composable
fun SimpleTextFieldPreview() {
    SimpleTextField(text = "test", label = "test label", onValueChange = {}, errorMessage = "I am an error")
}

@Preview(showBackground = true)
@Composable
fun RecipeInputFieldPreview() {
    RecipeInputField(text = "Test", label = "test label" ) {}
}