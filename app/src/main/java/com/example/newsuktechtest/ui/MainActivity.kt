package com.example.newsuktechtest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsuktechtest.ui.coins.CoinsScreen
import com.example.newsuktechtest.ui.coins.CoinsViewModel
import com.example.newsuktechtest.ui.theme.NewsUkTechTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsUkTechTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val coinsViewModel = hiltViewModel<CoinsViewModel>()
                    CoinsScreen(
                        coinsViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
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
    NewsUkTechTestTheme {
        Greeting("Android")
    }
}
