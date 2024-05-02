package com.example.newsuktechtest.data.api

import com.example.newsuktechtest.data.models.dto.CoinDTO
import com.example.newsuktechtest.data.models.dto.CoinsDTO
import com.example.newsuktechtest.utils.Constants.COINS
import com.example.newsuktechtest.utils.Constants.COIN_DETAIL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CoinsService {

    @Headers("Accept:application/json")
    @GET(COINS)
    suspend fun getCoins(): Response<List<CoinsDTO>>

    @Headers("Accept:application/json")
    @GET(COIN_DETAIL)
    suspend fun getCoinDetail(
        @Path(value = "id") id: String
    ) : Response<CoinDTO>
}
