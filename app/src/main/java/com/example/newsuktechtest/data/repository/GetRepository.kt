package com.example.newsuktechtest.data.repository

import com.example.newsuktechtest.data.models.Coin
import com.example.newsuktechtest.data.models.Coins

interface GetRepository {

    suspend fun getCoins(): Result<Coins>

    suspend fun getCoinDetail(coinID: String): Result<Coin>
}
