package com.delliott.recipes.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HandleErrors(errorMessage: String) {
    Column {
        Text(
            text = errorMessage,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        CircularProgressIndicator(
            modifier = Modifier
                .padding(all = 64.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.background,

            )
    }
}

@Preview(showBackground = true)
@Composable
fun HandleErrorPreview() {
    HandleErrors(errorMessage = "errorMessage")
}