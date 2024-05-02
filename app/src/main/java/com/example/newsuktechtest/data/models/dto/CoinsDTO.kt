package com.example.newsuktechtest.data.models.dto

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson


@JsonClass(generateAdapter = true)
data class CoinsDTO(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: String,
    @Json(name = "is_new") val isNew: Boolean,
    @Json(name = "is_active") val isActive: Boolean,
    val type: String
)
enum class CoinType(val value: String) {
    Coin("coin"),
    Token("token"),
    Unknown("unknown");

    companion object {
        public fun fromValue(value: String): CoinType = when (value) {
            "coin"  -> Coin
            "token" -> Token
            else    -> throw IllegalArgumentException()
        }
    }
}
/*object CoinTypeAdapter{
    @ToJson fun toJson(type: CoinType): String = type.name
    @FromJson fun fromJson(name: String): CoinType = CoinType.values().find { it.name == name }
        ?: CoinType.Unknown(
            name
        )
}*/

