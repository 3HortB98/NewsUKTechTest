package com.example.newsuktechtest.data.models

class CoinDetails (
    val symbol: String?,
    val logo: String?,
    val isActive: Boolean,
    val openSource: Boolean,
    val description: String?,
    val whitepaper: Whitepaper,
    val developmentStatus: String?,
    val startedAt: String?
)

class Whitepaper(
    val link: String?,
    val thumbnail: String?
)
