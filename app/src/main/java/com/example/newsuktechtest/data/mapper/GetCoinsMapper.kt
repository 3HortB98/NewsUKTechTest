package com.example.newsuktechtest.data.mapper

import com.example.newsuktechtest.data.models.Coin
import com.example.newsuktechtest.data.models.Coins
import com.example.newsuktechtest.data.models.dto.CoinsDTO
import javax.inject.Inject

class GetCoinsMapper @Inject constructor(
){

    fun mapCoins(coinsDTO: List<CoinsDTO>): Result<Coins>{
        return Result.success(
            Coins(
                coins = coinsDTO. map {
                    Coin(
                        name = it.name,
                        id = it.id,
                        type = it.type,
                        coinDetails = null
                    )
                } as ArrayList<Coin>
            )
        )
    }
}
