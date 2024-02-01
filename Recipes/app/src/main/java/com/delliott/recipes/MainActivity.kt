package com.delliott.recipes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.delliott.recipes.data.NetworkResult
import com.delliott.recipes.data.RecipeRepository
import com.delliott.recipes.ui.theme.RecipesTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = RecipeRepository()

        GlobalScope.launch {
            val result = repo.getAllRecipes()
            when (result) {
                is NetworkResult.ApiException -> {
                    Toast.makeText(this@MainActivity, "Error: ${result.e.message}", Toast.LENGTH_SHORT)
                }
                is NetworkResult.ApiSuccess -> {
                    val recipes = result.data
                    // Show recipe list
                }
                else -> {}
            }
        }

        setContent {
            RecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipesTheme {
        Greeting("Android")
    }
}