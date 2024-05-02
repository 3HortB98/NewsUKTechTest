package com.example.newsuktechtest.data.mapper

import com.example.newsuktechtest.data.models.Coin
import com.example.newsuktechtest.data.models.CoinDetails
import com.example.newsuktechtest.data.models.Whitepaper
import com.example.newsuktechtest.data.models.dto.CoinDTO
import javax.inject.Inject

class GetCoinMapper @Inject constructor(
) {
    fun mapCoinDetails(coinDTO: CoinDTO): Result<Coin>{

        return Result.success(
            Coin(
                name = coinDTO.name,
                id = coinDTO.id,
                type = coinDTO.type,
                coinDetails = CoinDetails(
                    symbol = coinDTO.symbol,
                    logo = coinDTO.logo,
                    isActive = coinDTO.isActive,
                    description = coinDTO.description,
                    openSource = coinDTO.openSource,
                    developmentStatus = coinDTO.developmentStatus,
                    startedAt = coinDTO.startedAt,
                    whitepaper = Whitepaper(
                        link = coinDTO.whitepaper.link,
                        thumbnail = coinDTO.whitepaper.thumbnail
                    )
                )
            )
        )

    }
}
