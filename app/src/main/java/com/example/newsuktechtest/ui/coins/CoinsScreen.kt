package com.example.newsuktechtest.ui.coins

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CoinsScreen(
    coinsViewModel: CoinsViewModel = viewModel(),
    modifier: Modifier = Modifier) {
    val uiState = coinsViewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Coins", fontSize = 20.sp)
        Button(onClick = {coinsViewModel.getData()}){
            Text(text = "Refresh")
        }

        when(uiState.value){
            is CoinsUiState.Loading ->{
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            is CoinsUiState.Success ->{

                LazyColumn(modifier = Modifier.fillMaxSize()){
                    val coins = (uiState.value as CoinsUiState.Success).coins
                    items(coins?.coins.orEmpty()){coin ->
                        CoinItem(coin)
                    }
                }

            }
            is CoinsUiState.Error -> {
                Text(text = (uiState.value as CoinsUiState.Error).message)}
        }


    }
}
