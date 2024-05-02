package com.example.newsuktechtest.data.api

import com.example.newsuktechtest.data.models.dto.CoinDTO
import com.example.newsuktechtest.data.models.dto.CoinsDTO
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: CoinsService) : ApiHelper {
    override suspend fun getCoins(): Response<List<CoinsDTO>> = apiService.getCoins()
    override suspend fun getCoinDetail(coinID: String): Response<CoinDTO> = apiService.getCoinDetail(coinID)

}
