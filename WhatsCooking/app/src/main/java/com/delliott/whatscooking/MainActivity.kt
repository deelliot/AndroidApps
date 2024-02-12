package com.delliott.whatscooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.delliott.whatscooking.ui.home.HomeScreen
import com.delliott.whatscooking.ui.home.HomeScreenViewModel
import com.delliott.whatscooking.ui.recipe.FullRecipeScreen
import com.delliott.whatscooking.ui.recipe.FullRecipeViewModel
import com.delliott.whatscooking.ui.search.SearchScreen
import com.delliott.whatscooking.ui.search.SearchViewModel
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

enum class RecipeScreen(
    @StringRes val title: Int,
    val route: String,
) {
    HomeScreen(title = R.string.homeScreen, "home"),
    FullRecipeScreen(title = R.string.FullRecipeScreen, "recipes/{recipeId}"),
    SearchScreen(title = R.string.searchScreen, route = "search/{searchTerm}")
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBar(
    currentScreen: RecipeScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun WhatsCookingApp(
    homeViewModel: HomeScreenViewModel = viewModel(),
    fullRecipeViewModel: FullRecipeViewModel = viewModel(),
    searchViewModel: SearchViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RecipeScreen.entries.firstOrNull {
        it.route == backStackEntry?.destination?.route
    } ?: RecipeScreen.HomeScreen
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(RecipeScreen.HomeScreen.route) {
                val uiState by homeViewModel.uiState.collectAsState()
                if (uiState.errorMessage != null) {
                    // TODO:
                }

                LaunchedEffect(key1 = Unit, block = {
                    homeViewModel.fetchAllRecipes()
                    // TODO: add loading bar
                })
                HomeScreen(
                    recipesTopRated = uiState.recipesTopRated,
                    recipes30Mins = uiState.recipes30mins,
                    searchTerm = uiState.searchTerm,
                    onMealTypeSelected = { mealType: String ->
                        homeViewModel.fetchRecipesByMealType(mealType)
                    },
                    onRecipeSelected = { recipeId: Int ->
                        navController.navigate("recipes/$recipeId")
                    },
                    onSearchChanged = { searchTerm: String ->
                        homeViewModel.setSearchTerm(searchTerm)
                        // navController.navigate("search/$searchTerm")
                        // TODO: add loading bar
                    },
                    onSearchClicked = {
                        navController.navigate("search/${uiState.searchTerm}")
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(RecipeScreen.FullRecipeScreen.route) { navBackStackEntry ->
                val recipeId = navBackStackEntry.arguments?.getString("recipeId")!!
                val uiState by fullRecipeViewModel.uiState.collectAsState()

                LaunchedEffect(key1 = recipeId, block = {
                    fullRecipeViewModel.fetchRecipeDetails(recipeId.toInt())
                })

                if (uiState.errorMessage != null) {
                    // TODO:
                } else if (uiState.recipeDetails != null) {
                    FullRecipeScreen(
                        uiState.recipeDetails!!,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
            composable(RecipeScreen.SearchScreen.route) { navBackStackEntry ->
                val searchTerm = navBackStackEntry.arguments?.getString("searchTerm")!!
                val uiState by searchViewModel.uiState.collectAsState()

                searchViewModel.fetchSearchRecipes(searchTerm)
                if (uiState.errorMessage != null) {
                    // TODO:
                } else if (uiState.recipes != null) {
                    SearchScreen(
                        searchTerm = searchTerm,
                        recipeList = uiState.recipes,
                        modifier = Modifier.fillMaxSize(),
                        onRecipeSelected = { recipeId: Int ->
                            navController.navigate("recipes/$recipeId")
                        })
                }
            }
        }
    }
}

