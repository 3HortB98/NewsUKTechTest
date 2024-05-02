package com.example.newsuktechtest.ui.coins

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsuktechtest.ui.coinDetail.ItemDetailDialog
import timber.log.Timber

@Composable
fun CoinsScreen(
    coinsViewModel: CoinsViewModel = viewModel(),
    modifier: Modifier = Modifier) {
    val uiState = coinsViewModel.uiState.collectAsState()
    val coinDetailsState = coinsViewModel.uiDetailsState.collectAsState()
    var showDetailsDialog by remember { mutableStateOf(false) }

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
                    items(coins?.coins?.sortedBy { it.name }.orEmpty()){coin ->
                        CoinItem(coin, onClick = {
                            coin.id?.let {
                                coinsViewModel.getCoinDetail(it)

                            }
                        })
                    }
                }

            }
            is CoinsUiState.Error -> {
                Text(text = (uiState.value as CoinsUiState.Error).message)}
        }

        when(coinDetailsState.value){
            is CoinDetailsUiState.Success ->{
                Timber.d("CoinState")
                showDetailsDialog = showDetailsDialog.not()
                val coin = (coinDetailsState.value as CoinDetailsUiState.Success).coin
                if (coin != null) {
//                    if (showDetailsDialog){
                        ItemDetailDialog(coin) {
                            showDetailsDialog = false
                            coinsViewModel.closeDetails()
                        }
                   // }

                }
            }

            is CoinDetailsUiState.Error -> {
                Dialog(onDismissRequest = {showDetailsDialog = false}) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Text(text = (coinDetailsState.value as CoinDetailsUiState.Error).message)
                    }
                }

            }
            is CoinDetailsUiState.Hidden -> {

            }
            is CoinDetailsUiState.Loading -> {

            }
        }

    }
}
