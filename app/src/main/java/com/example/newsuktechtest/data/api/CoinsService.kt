package com.example.newsuktechtest.data.api

import com.example.newsuktechtest.data.models.dto.CoinsDTO
import com.example.newsuktechtest.utils.Constants.COINS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CoinsService {

    @Headers("Accept:application/json")
    @GET(COINS)
    suspend fun getCoins(): Response<List<CoinsDTO>>
}
