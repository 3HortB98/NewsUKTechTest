package com.example.newsuktechtest.data.models.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CoinsDTO(
    val id: String? = null,
    val name: String? = null,
    val symbol: String? = null,
    val rank: String? = null,
    @Json(name = "is_new") val isNew: Boolean,
    @Json(name = "is_active") val isActive: Boolean,
    val type: String? = null

)
