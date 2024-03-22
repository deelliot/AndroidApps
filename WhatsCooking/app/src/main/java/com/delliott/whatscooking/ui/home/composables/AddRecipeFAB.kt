package com.delliott.whatscooking.ui.home.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddRecipeFAB(modifier: Modifier, context: Context, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = modifier,
        onClick = {
            Toast.makeText(context, "Floating action button", Toast.LENGTH_LONG).show()
            onClick()
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 8.dp
        )
//        shape = CircleShape
    ) {
        Icon(Icons.Filled.Add, "Add Recipe Floating Action Button.")
    }
}