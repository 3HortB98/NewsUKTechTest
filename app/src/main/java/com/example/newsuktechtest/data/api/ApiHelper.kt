package com.example.newsuktechtest.data.api

import com.example.newsuktechtest.data.models.dto.CoinDTO
import com.example.newsuktechtest.data.models.dto.CoinsDTO
import retrofit2.Response

interface ApiHelper {
    suspend fun getCoins(): Response<List<CoinsDTO>>

    suspend fun getCoinDetail(coinID: String): Response<CoinDTO>
}
