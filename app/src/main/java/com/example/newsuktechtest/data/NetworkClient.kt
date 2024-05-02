package com.example.newsuktechtest.data

import com.example.newsuktechtest.data.api.CoinsService
import com.example.newsuktechtest.data.mapper.GetCoinMapper
import com.example.newsuktechtest.data.mapper.GetCoinsMapper
import com.example.newsuktechtest.data.models.Coin
import com.example.newsuktechtest.data.models.Coins
import com.example.newsuktechtest.data.repository.GetRepository
import timber.log.Timber
import javax.inject.Inject

class NetworkClient @Inject constructor(
    val apiService: CoinsService,
    val coinsMapper: GetCoinsMapper,
    val coinDetailMapper: GetCoinMapper
) : GetRepository {
    override suspend fun getCoins(): Result<Coins> {
        return try {
            val response = apiService.getCoins()
            Timber.d(response.message())
            if (response.isSuccessful && response.body() != null){
                return coinsMapper.mapCoins(requireNotNull(response.body()))
            } else {
                Timber.tag("failed").e(response.message())
                Result.failure(Exception("Network Error"))
            }
        } catch (exceptions: Exception){
            return Result.failure(exceptions)
        }
    }

    override suspend fun getCoinDetail(coinID: String): Result<Coin> {
        return try {
            val response = apiService.getCoinDetail(coinID)
            Timber.d(response.message())
            if (response.isSuccessful && response.body() != null){
                return coinDetailMapper.mapCoinDetails(requireNotNull(response.body()))
            } else {
                Timber.tag("failed").e(response.message())
                Result.failure(Exception("Network Error"))
            }
        } catch (exceptions: Exception){
            return Result.failure(exceptions)
        }
    }
}
