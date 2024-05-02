package com.example.newsuktechtest.ui.coins

import com.example.newsuktechtest.data.models.Coin
import com.example.newsuktechtest.data.models.Coins

sealed class CoinsUiState {
    object Loading: CoinsUiState()
    data class Success(val coins: Coins?): CoinsUiState()
    data class Error(val message: String): CoinsUiState()
}

sealed class CoinDetailsUiState{
    object Hidden: CoinDetailsUiState()
    object Loading: CoinDetailsUiState()
    data class Success(val coin: Coin?): CoinDetailsUiState()
    data class Error(val message: String): CoinDetailsUiState()
}
