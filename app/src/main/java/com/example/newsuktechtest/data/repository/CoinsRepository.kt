package com.example.newsuktechtest.data.repository

import com.example.newsuktechtest.data.api.ApiHelper
import javax.inject.Inject

class CoinsRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getCoins() = apiHelper.getCoins()

    suspend fun getCoinDetail(coinID:String) = apiHelper.getCoinDetail(coinID)
}
