package com.example.newsuktechtest.utils

object Constants {
    const val CACHE_SIZE :Long= 5 * 1024 * 1024
    const val API_TIMEOUT :Long = 30
    const val API = "https://api.coinpaprika.com/v1/"
    const val COINS = "coins"
    const val COIN_DETAIL = "coins/{id}"
    const val COINS_URI = "coinsURI"
    const val COINS_CLIENT = "coinsClient"

    //Get Coins: https://api.coinpaprika.com/v1/coins
    //Get Coin By Id: https://api.coinpaprika.com/v1/coins/{id}
    //https://api.coinpaprika.com/v1/coins/btc-bitcoin
}
