package com.delliott.whatscooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delliott.whatscooking.ui.HomeScreenViewModel
import com.delliott.whatscooking.ui.composables.HomeScreen
import com.delliott.whatscooking.ui.theme.WhatsCookingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsCookingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WhatsCookingApp()
                }
            }
        }
    }
}

@Composable
fun WhatsCookingApp(
    viewModel: HomeScreenViewModel = viewModel()
    // navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()
    if (uiState.errorMessage != null) {
        //todo
    } else {
        HomeScreen(
            recipes30Mins = uiState.recipes30mins,
            recipesTopRated = uiState.recipesTopRated,
            onMealTypeSelected = { mealType: String ->
                viewModel.fetchRecipesByMealType(mealType)
            }
        )
    }

}

