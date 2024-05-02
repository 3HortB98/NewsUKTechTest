package com.example.newsuktechtest.data.mapper

import com.example.newsuktechtest.data.models.Coin
import com.example.newsuktechtest.data.models.CoinDetails
import com.example.newsuktechtest.data.models.dto.CoinDTO
import javax.inject.Inject

class GetCoinMapper @Inject constructor(
) {
    fun mapCoinDetails(coinDTO: CoinDTO): Result<Coin>{

        return Result.success(
            Coin(
                name = coinDTO.name,
                id = coinDTO.id,
                coinDetails = CoinDetails(
                    symbol = coinDTO.symbol,
                    logo = coinDTO.logo
                )
            )
        )

    }
}
